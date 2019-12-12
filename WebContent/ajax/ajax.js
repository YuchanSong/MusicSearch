$(document).ready(function() {
	jQuery.fn.serializeObject = function() {
	    var obj = null;
	    try {
	        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
	        	// "name":"title", "value":"술이 문제야" 형식으로 반환함
	        	var arr = this.find(":input:not(:hidden)").serializeArray();
	        	// hidden 속성 제외하고 serializeArray 생성
	        	
	            if (arr) {
	                obj = {};
	                // 그렇기 때문에 JSON 형식으로 변환해줌
	                jQuery.each(arr, function() {
	                    obj[this.name] = this.value;
	                });
	            } //if ( arr ) {
	        }
	    } catch (e) {
	        alert(e.message);
	    }
	 
	    return obj;
	};

	function ajaxToGet() {
		var url = "http://localhost:8080/test/uchan";
		
		$('input[name=search]').attr("value", true);
		// form1의 input tag에서 search=true&name=value 형식으로 리턴
		var params = $("#form1").find(":input:hidden").serialize(); // hidden만 리턴
		$('input[name=search]').attr("value", false);
		
		console.log("doGet");
		console.log("params : " + params);
		
		$.ajax({
			type:"Get",
			url:url,
			data:params,
			dataType:"json",
			success:function(responseData) {
				console.log("From to Server : " + JSON.stringify(responseData));

				$("input[name=title]").val("");
				$("#result").find("h3").remove();
				var i = 1;
				$.each(responseData, function(key) {
					$("<h3>").text(i + " : " + key).appendTo($("#result"));
					i++;
				});
			},
			error:function(e) {
				alert(e.responseText);
			}
		});
	}
	
	$.fn.multiLine = function(text){
	    this.text(text);
	    this.html(this.html().replace(/\n/g, '<br/>'));
	    return this;
	}
	
	function ajaxToPost() {
		if (!$("#title").val().length) {
			alert("노래 제목을 입력해주세요.");
			return;
		}
		
		var url = "http://localhost:8080/test/uchan";
		var params = $("#form1").serializeObject();
		console.log("doPost");
		console.log("params : " + JSON.stringify(params));
		
		$.ajax({
			type:"POST",
			url:url,
			data:{"requestData":JSON.stringify(params)},
			dataType:"json",
			success:function(responseData) {
				console.log("From to Server : " + JSON.stringify(responseData));

				$("input[name=title]").val("");
		        $("#result").find("h3").remove();
		        $("<h3>").text(responseData.title).appendTo($("#result"));
		        $("<p>").css("fontSize", 14).val("lyrics").multiLine(responseData.lyrics).appendTo($("h3"));
			},
			error:function(e) {
				console.log("Post error");
				alert(e.responseText);
			}
		});
	}
	
	$("#btnGetSend").click(function() {
		ajaxToGet();
	});
	
	$("#btnPostSend").click(function() {
		ajaxToPost();
	});
	
	$("input[name=title]").keydown(function(e) {
		if (e.keyCode == 13) {
			ajaxToPost();
		}
	});

});