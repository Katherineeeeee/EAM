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

		returnProperty: function(id) {
			let that = this;
			jq.ajax({
				type: "POST",
				url: "/api/user/applications/id/" + id,
				dataType: "json",
				success: function(data) {
					if (data.status === 302) {
						that.$notify({
							title: "归还成功",
							type: "success",
							duration: "1000",
							onClose: function() {
								window.location.reload();
							}
						});
					} else {
						that.$notify.error({
							title: "归还失败",
							message: "归还失败..."
						});
					}
				}
			});
		}
		
	}
});