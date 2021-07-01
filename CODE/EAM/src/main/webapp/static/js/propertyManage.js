var jq = jQuery.noConflict();
var vm = new Vue({
	el: "#property_manage",
	data() {
		var validateManagerPass = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请输入密码'));
			} else {
				if (this.manager.password.length < 5 || this.manager.password.length > 20) {
					callback(new Error('密码长度不对'));
				}
				if (this.manager.password !== '') {
					this.$refs.manager.validateField('password2');
				}
				callback();
			}
		};
		var validateManagerPass2 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.manager.password) {
				callback(new Error('两次输入密码不一致!'));
			} else {
				callback();
			}
		};
		var validateTelephone = (rule, value, callback) => {
			const reg = /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
			if(value==''||value==undefined||value==null){
				callback();
			}else {
				if((!reg.test(value)) && value !='') {
					callback(new Error('请输入正确的手机号'));
				} else {
					callback();
				}
			}
		};
		var validateEMail = (rule, value,callback) => {
			const reg =/^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
			if(value==''||value==undefined||value==null){
				callback();
			}else{
				if(!reg.test(value)){
					callback(new Error('请输入正确的邮箱'));
				} else {
					callback();
				}
			}
		};
		var validateNumber = (rule, value, callback) => {
			if (value==''|| value==undefined||value==null) {
				callback();
			}else {
				if (!/^\d+$/.test(value)) {
					callback(new Error("请输入整数"));
				} else {
					let num = Number(value);
					if (num < 1) {
						callback(new Error("数量至少为一个"));
					} else {
						callback();
					}
				}
			}
		};
		return {
			formLabelWidth: '120px',
			userSelected: [],
			infoDialog: false,
			propertyAddDialog: false,
			manager: {
				name: '',
				password: '',
				phone: '',
				email: ''
			},
			managerRules: {
				password: [
					{ validator: validateManagerPass, trigger: 'blur' }
				],
				password2: [
					{ validator: validateManagerPass2, trigger: 'blur' }
				],
				phone: [
					{validator: validateTelephone, trigger: 'blur'}
				],
				email: [
					{validator: validateEMail, trigger: 'blur'}
				]
			},
			propertyAddForm: {
				name: '',
				brand: '',
				type: '',
				standard: '',
				// time: '',
				num: 1
			},
			propertyAddRules: {
				name: [
					{required: true, message: '请输入用户名', trigger: 'blur'}
				],
				brand: [
					{required: true, message: '请输入品牌', trigger: 'blur'}
				],
				type: [
					{required: true, message: '请输入型号', trigger: 'blur'}
				],
				standard: [
					{required: true, message: '请输入规格', trigger: 'blur'}
				],
				// time: [
				// 	{type: "date", required: true, message: '请输入采购日期', trigger: 'blur'}
				// ],
				num: [
					{validator: validateNumber, trigger: 'blur'}
				]
			}
		}
	},
	methods: {
		
		self: function(command) {
			if (command == "updateInfo") {
				vm.$data.infoDialog = true;
			} else if (command == "logout") {
				window.location.href = "/api/auth/logout/manager";
			}
		},

		/**
		 * 获取当前管理员的信息
		 */
		getInfo: function() {
			let that = this;
			jq.ajax({
				type: 'GET',
				url: '/api/manager/self',
				success: function (data) {
					if (data.status === 200) {
						let info = that.decode(data.msg);
						that.manager.name = info.name;
						that.manager.phone = info.phone;
						that.manager.email = info.email;
					} else {
						that.$notify.error({
							title: '修改失败',
							message: '请重试'
						});
					}
				}
			});
		},

		/**
		 * 修改当前管理员的信息
		 */
		updateInfo: function() {
			let that = this;
			vm.$data.infoDialog = false;
			let info = "password:" + that.manager.password +
				"\nphone:" + that.manager.phone +
				"\nemail:" + that.manager.email;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			jq.ajax({
				type: "PUT",
				url: "/api/manager/self",
				data: {
					"info": info
				},
				dataType: "json",
				success: function (data) {
					if (data.status === 200) {
						that.$notify({
							title: '修改成功',
							message: '请重新登录',
							type: 'success',
							duration: '1500',
							onClose: function() {
								window.location.href = "/";
							}
						});
					} else {
						that.$notify.error({
							title: '修改失败',
							message: '请重试'
						});
					}
				}
			});
		},


		cancelUpdateInfo: function (formName) {
			this.$refs[formName].resetFields();
			this.infoDialog = false;
		},

		/**
		 * 添加资产
		 * @param formName 表单名字, 用于前台简单的检查数据格式
		 */
		addProperty: function(formName) {
			let that = this;
			let info = "name:" + that.propertyAddForm.name +
						"\nbrand:" + that.propertyAddForm.brand +
						"\nspec:" + that.propertyAddForm.standard +
						"\nnum:" + that.propertyAddForm.num +
						"\nmodel:" + that.propertyAddForm.type;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			this.$refs[formName].validate((valid) => {
				if (valid) {
					that.propertyAddDialog = false;
					jq.ajax({
						type: "POST",
						url: "/api/manager/properties",
						data: {
							"info": info
						},
						dataType: "json",
						success: function (data) {
							if (data.status === 302) {
								that.$notify({
									title: '添加成功',
									type: 'success',
									duration: '1500',
									onClose: function() {
										window.location.href = data.msg;
									}
								});
							} else {
								that.$refs[formName].resetFields();
								that.$notify.error({
									title: '添加失败',
									message: data.msg
								});
							}
						}
					});
				} else {
					return false;
				}
			});
		},

		cancelAddProperty: function(formName) {
			this.$refs[formName].resetFields();
			this.propertyAddDialog = false;
		},

		decode: function (msg) {
			let info = {};
			msg = Base64.decode(Base64.decode(Base64.decode(msg)));
			let arrMsg = msg.split("\n");
			for (let m of arrMsg) {
				let kv = m.split(":");
				info[kv[0]] = kv[1];
			}
			return info;
		}
	}
});