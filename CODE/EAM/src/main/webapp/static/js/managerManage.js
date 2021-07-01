var jq = jQuery.noConflict();
var vm = new Vue({
	el: "#managerManage",
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
		var validatePass = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请输入密码'));
			} else {
				if (this.managerAddForm.password.length < 5 || this.managerAddForm.password.length > 20) {
					callback(new Error('密码长度不对'));
				}
				if (this.managerAddForm.password !== '') {
					this.$refs.managerAddForm.validateField('password2');
				}
				callback();
			}
		};
		var validatePass2 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.managerAddForm.password) {
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

		return {
			formLabelWidth: '120px',
			managerSelectedAll: false,
			managerSelected: [],
			infoDialog: false,
			managerAddDialog: false,
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
			managerAddForm: {
				name: '',
				password: '',
				password2: '',
				phone: '',
				email: ''
			},
			managerAddRules: {
				name: [
					{required: true, message: '请输入用户名', trigger: 'blur'},
					{ min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' }
				],
				password: [
					{ validator: validatePass, trigger: 'blur' }
				],
				password2: [
					{ validator: validatePass2, trigger: 'blur' }
				],
				phone: [
					{ validator: validateTelephone, trigger: 'blur'}
				],
				email: [
					{validator: validateEMail, trigger: 'blur'}
				]
			}
		}
	},
	methods: {
		
		self: function(command) {
			if (command === "updateInfo") {
				vm.$data.infoDialog = true;
			} else if (command === "logout") {
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
		updateInfo: function(formName) {
			let that = this;
			vm.$data.infoDialog = false;
			let info = "password:" + that.manager.password +
				"\nphone:" + that.manager.phone +
				"\nemail:" + that.manager.email;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			this.$refs[formName].validate((valid) => {
				if (valid) {
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
				} else {
					return false;
				}
			})
		},


		cancelUpdateInfo: function (formName) {
			this.$refs[formName].resetFields();
			this.infoDialog = false;
		},

		/**
		 * 添加管理员
		 * @param formName 表单名字, 用于前台简单的检查数据格式
		 */
		addManager: function(formName) {
			let that = this;
			let info = "name:" + this.managerAddForm.name +
						"\npassword:" + this.managerAddForm.password +
						"\nemail:" + this.managerAddForm.email +
						"\nphone:" + this.managerAddForm.phone;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			this.$refs[formName].validate((valid) => {
				if (valid) {
					this.managerAddDialog = false;
					jq.ajax({
						type: "POST",
						url: "/api/manager/managers",
						data: {
							info: info
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

		deleteManagers: function () {
			let that = this;
			jq.ajax({
				type: "DELETE",
				url: "/api/manager/managers",
				data: JSON.stringify(that.managerSelected),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					if (data.status === 302) {
						window.location.href = data.msg;
					} else {
						that.$notify.error({
							title: "删除失败",
							message: "删除失败"
						});
					}
				}
			});
		},

		updateManagerStatus: function (id, status) {
			let that = this;
			jq.ajax({
				type: "PATCH",
				url: "/api/manager/managers/id/" + id + "/status/" + status,
				success: function (data) {
					if (data.status === 302) {
						window.location.href = data.msg;
					} else {
						that.$notify.error({
							title: '修改失败',
							message: '请重试'
						});
					}
				}
			});
		},
		
		cancelAddManager: function(formName) {
			this.$refs[formName].resetFields();
			this.managerAddDialog = false;
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
		},
		
		selectOne: function(id, status) {
			let ix = this.managerSelected.indexOf(id);
			if (ix > -1) {
				this.managerSelected.splice(ix, 1);
			}
			if (status) {
				this.managerSelected.push(id);
			} else {
				this.managerSelectedAll = false;
			}
			console.log(this.managerSelected);
		},
			
		selectAll: function() {
			this.managerSelected.length = 0;
			let table = document.getElementById("managerTable");
			let rows = table.getElementsByTagName("tr");
			let status = this.managerSelectedAll ? " is-checked" : "";
			let arr = [];
			for (let i = 1; i < rows.length; i++) {
				let r = rows[i];
				r.getElementsByClassName("el-checkbox")[0].setAttribute("class", "el-checkbox" + status);
				r.getElementsByClassName("el-checkbox__input")[0].setAttribute("class", "el-checkbox__input" + status);
				arr.push(parseInt(r.getElementsByTagName("td")[1].innerText));
			}
			if (this.managerSelectedAll) {
				this.managerSelected = arr;
			}
			console.log(this.managerSelected);
		}
	}
});