var jq = jQuery.noConflict();
var vm = new Vue({
	el: "#userManage",
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
				if (this.userAddForm.password.length < 5 || this.userAddForm.password.length > 20) {
					callback(new Error('密码长度不对'));
				}
				if (this.userAddForm.password !== '') {
					this.$refs.userAddForm.validateField('password2');
				}
				callback();
			}
		};
		var validatePass2 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.userAddForm.password) {
				callback(new Error('两次输入密码不一致!'));
			} else {
				callback();
			}
		};
		var validatePass3 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请输入密码'));
			} else {
				if (this.userChangeForm.password.length < 5 || this.userChangeForm.password.length > 20) {
					callback(new Error('密码长度不对'));
				}
				if (this.userChangeForm.password !== '') {
					this.$refs.userChangeForm.validateField('password2');
				}
				callback();
			}
		};
		var validatePass4 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.userChangeForm.password) {
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
			userSelectedAll: false,
			userSelected: [],
			infoDialog: false,
			userAddDialog: false,
			userChangeDialog: false,
			manager: {
				name: '',
				password: '',
				password2: '',
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
			userAddForm: {
				name: '',
				password: '',
				password2: '',
				email: ''
			},
			userAddRules: {
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
				email: [
					{ validator: validateEMail, trigger: 'blur'}
				]
			},
			userChangeId: 0,
			userChangeForm: {
				name: '',
				password: '',
				password2: '',
				email: ''
			},
			userChangeRules: {
				password: [
					{ validator: validatePass3, trigger: 'blur' },
					{ min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' }
				],
				password2: [
					{ validator: validatePass4, trigger: 'blur' }
				],
				email: [
					{ validator: validateEMail, trigger: 'blur'}
				]
			}
		}
	},
	methods: {
		
		self: function(command) {
			if (command == "updateInfo") {
				this.infoDialog = true;
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
					info: info,
					_method: "put"
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
		 * 增加用户
 		 * @param formName 表单名字, 用于前台简单的检查数据格式
		 */
		addUser: function(formName) {
			let that = this;
			let info = "username:" + that.userAddForm.name +
						"\npassword:" + that.userAddForm.password +
						"\nemail:" + that.userAddForm.email;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			that.$refs[formName].validate((valid) => {
				if (valid) {
					that.userAddDialog = false;
					jq.ajax({
						type: "POST",
						url: "/api/manager/users",
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

		/**
		 * 取消添加用户
 		 * @param formName 表单名字, 用于前台清空数据
		 */
		cancelAddUser: function(formName) {
			this.$refs[formName].resetFields();
			this.userAddDialog = false;
		},

		/**
		 * 删除this.userSelected中的用户
		 */
		deleteUsers: function() {
			let that = this;
			jq.ajax({
				type: "DELETE",
				url: "/api/manager/users",
				data: JSON.stringify(that.userSelected),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					if (data.status === 302) {
						window.location.href = data.msg;
					} else {
						that.$notify.error({
							title: "服务器异常",
							message: "删除失败"
						});
					}
				}
			});
		},

		updateUser: function(id) {
			this.userChangeId = id;
			this.getUserDetail(id);
			this.userChangeDialog = true;
		},

		/**
		 * 获取id对应用户的详情信息
		 * @param id 用户的id
		 */
		getUserDetail: function(id) {
			let that = this;
			jq.ajax({
				type: "GET",
				url: "/api/manager/users/id/" + id,
				success: function (data) {
					if (data.status === 200) {
						let userInfo = that.decode(data.msg);
						that.userChangeForm.name = userInfo.name;
						that.userChangeForm.email = userInfo.email;
					}
				}
			});
		},

		/**
		 * 更新id对应用户的状态为status
		 * @param id 用户的id
		 * @param status 要更新为的status
		 */
		updateUserStatus: function(id, status) {
			jq.ajax({
				type: "PATCH",
				url: "/api/manager/users/id/" + id + "/status/" + status,
				success: function (data) {
					if (data.status === 302) {
						window.location.href = data.msg;
					}
				}
			});
		},

		/**
		 * 更新用户的信息
		 * @param formName 表单名字, 用于前台简单的检查数据格式
		 */
		updateUserDetail: function(formName) {
			let that = this;
			let info = "username:" + that.userChangeForm.name +
				"\npassword:" + that.userChangeForm.password +
				"\nemail:" + that.userChangeForm.email;
			info = Base64.encode(Base64.encode(Base64.encode(info)));
			this.$refs[formName].validate((valid) => {
				if (valid) {
					this.userAddDialog = false;
					jq.ajax({
						type: "PUT",
						url: "/api/manager/users/id/" + that.userChangeId,
						data: {
							"info": info
						},
						dataType: "json",
						success: function (data) {
							if (data.status === 200) {
								that.$notify({
									title: "更新成功",
									type: 'success',
									duration: '1000',
									onClose: function() {
										window.location.reload();
									}
								});
							} else {
								that.$refs[formName].resetFields();
								that.$notify.error({
									title: '更新失败',
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

		/**
		 * 取消更新用户的信息
		 * @param formName 表单名字, 用于前台清空数据
		 */
		cancelUpdateUserDetail: function(formName) {
			this.$refs[formName].resetFields();
			this.userChangeDialog = false;
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
			let ix = this.userSelected.indexOf(id);
			if (ix > -1) {
				this.userSelected.splice(ix, 1);
			}
			if (status) {
				this.userSelected.push(id);
			} else {
				this.userSelectedAll = false;
			}
		},
		
		selectAll: function() {
			this.userSelected.length = 0;
			let table = document.getElementById("userTable");
			let rows = table.getElementsByTagName("tr");
			let status = this.userSelectedAll ? " is-checked" : "";
			let arr = [];
			for (let i = 1; i < rows.length; i++) {
				let r = rows[i];
				r.getElementsByClassName("el-checkbox")[0].setAttribute("class", "el-checkbox" + status);
				r.getElementsByClassName("el-checkbox__input")[0].setAttribute("class", "el-checkbox__input" + status);
				arr.push(parseInt(r.getElementsByTagName("td")[1].innerText));
			}
			if (this.userSelectedAll) {
				this.userSelected = arr;
			}
			
			console.log(this.userSelected);
		}
	}
});