var jq = jQuery.noConflict();
var user = new Vue({
	el: "#user",
	data() {
		return {
			
		}
	},
	methods: {

		self: function(command) {
			if (command == "logout") {
				window.location.href = "/api/auth/logout/user";
			}
		},

		applyProperty: function(id) {
			let that = this;
			jq.ajax({
				type: "POST",
				url: "/api/user/properties/id/" + id,
				dataType: "json",
				success: function(data) {
					if (data.status === 302) {
						that.$notify({
							title: "申请成功",
							type: "success",
							duration: "1000",
							onClose: function() {
								window.location.href = data.msg;
							}
						});
					} else {
						that.$notify.error({
							title: "申请失败",
							message: data.msg
						});
					}
				}
			});
		},
		
	}
});