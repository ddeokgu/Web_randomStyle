<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="layer"
		style="display: block; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
	</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		window.addEventListener("message", onReceivedPostMessage, false);
		function onReceivedPostMessage(event) {
			var action = event.data.action;
			var params = event.data.params;
		}
		function onReceivedActivityMessageViaJavascriptInterface(json) {
			var data = JSON.parse(json);
			var action = data.action;
			var params = data.params;
		}
		var element_layer = document.getElementById('layer');
		function getPostCode() {
			new daum.Postcode({
				oncomplete : function(data) {
					var fullAddr = data.address;
					var extraAddr = '';
					if (data.addressType === 'R') {
						if (data.bname !== '') {
							extraAddr += data.bname;
						}
						if (data.buildingName !== '') {
							extraAddr += (extraAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')'
								: '');
					}
					var fullRoadAddr = data.roadAddress;
					var extraRoadAddr = '';
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraRoadAddr += data.bname;
					}
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraRoadAddr += (extraRoadAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					if (extraRoadAddr !== '') {
						extraRoadAddr = ' (' + extraRoadAddr + ')';
					}
					if (fullRoadAddr !== '') {
						fullRoadAddr += extraRoadAddr;
					}
					window.android.putAddress(data.zonecode, fullRoadAddr);
				},
				width : '100%',
				height : '100%'
			}).embed(element_layer);
			element_layer.style.display = 'block';
			initLayerPosition();
		}
		function initLayerPosition() {
			var width = (window.innerWidth || document.documentElement.clientWidth);
			var height = (window.innerHeight || document.documentElement.clientHeight);
			var borderWidth = 5;
			element_layer.style.width = width + 'px';
			element_layer.style.height = height + 'px';
			element_layer.style.border = borderWidth + 'px solid';
			element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
					+ 'px';
			element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
					+ 'px';
		}
	</script>
</body>
</html>

