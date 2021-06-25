var jq = jQuery.noConflict();
var vm = new Vue({
	el: "#app",
	data() {
		return {
			username: '',
			password: ''
		}
	},
	methods: {
		userLogin: function() {
			var that = this;
			var info = "username:" + that.username + "\npassword:" + that.password;
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			jq.ajax({
				type: "POST",
				url: "/api/auth/users",
				data: {
					"info": info
				},
				dataType: "json",
				success: function (data) {
					if (data.status == 302) {
						window.location.href = data.msg;
					} else {
						this.$notify({
							title: '服务器异常',
							message: '服务器异常, 登录失败'
						});
					}
				}
			});
		},
		
		managerLogin: function() {
			var that = this;
			var info = "username:" + that.username + "\npassword:" + that.password;
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			info = Base64.encode(info);
			console.log(info);
			jq.ajax({
				type: "POST",
				url: "/api/auth/managers",
				data: {
					"info": info
				},
				dataType: "json",
				success: function (data) {
					console.log(data);
					if (data.status == 302) {
						console.log(data.msg);
						window.location.href = data.msg;
					} else {
						this.$notify({
							title: '服务器异常',
							message: '服务器异常, 登录失败'
						});
					}
				}
			});
		}
	}
});