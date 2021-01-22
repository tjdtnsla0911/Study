$(document).ready(function(){
	console.log('시작 = ',$('input:radio[name="radioSale"]:checked').val());
    // 라디오버튼 클릭시 이벤트 발생
    $("input:radio[name=radioSale]").click(function(){
        if($("input:radio[name=radioSale]:checked").val() == 'true'){
            $(".radioSale").attr("disabled",false);
            // radio 버튼의 value 값이 1이라면 활성화
        	console.log($('input:radio[name="radioSale"]:checked').val());
        }else if($("input:radio[name=radioSale]:checked").val() == 'false'){
              $(".radioSale").attr("disabled",true);
          	console.log($('input:radio[name="radioSale"]:checked').val());
            // radio 버튼의 value 값이 0이라면 비활성화
        }
    });
});

	var sel_file;

	$(document).ready(function() {
		$("#input_img").on("change", handleImgFileSelect);
	});
	console.log($("#input_img").val());

	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}


let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.save();
			});
			$(".btn-update").on("click", (e)=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				console.log('업데이트에옴');
				this.update(e);
			});
			$(".btn-delete").on("click", (e)=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.userDelete(e);
			});
			$("#start").on("click",()=>{
				this.start();
			});
			$(".btn-ViewDetails").on("click", (e)=>{ // function(){} , ()=>{} this를

this.ViewDetails(e);
});

		},

		save: function(){
		// alert('user의 save함수 호출됨');
			let data = {
					parentProductId:$("#parentProductId").val(),
					relateProductId:$("#relateProductId").val(),
					title: $("#title").val(),
					thumb: $("#thumb").val(),
					price: $("#price").val(),
					disc: $("#disc").val(),
					discounted: $("#discounted").val(),
					content: $("#content").val(),
					radioSale : $('input:radio[name="radioSale"]:checked').val(),
					radioAd : $('input:radio[name="radioAd"]:checked').val(),
					radioParentTypeId : $('input:radio[name="radioParentTypeId"]:checked').val(),
					radioBest : $('input:radio[name="radioBest"]:checked').val(),
					radioNew : $('input:radio[name="radioNew"]:checked').val(),
			};
			console.log('data = ',data);
			if(data.bgImg.length < 444){
				alert('사진미리보기를 누르지 않으셨습니다.');

					return;

			}

			if(fuck=="null"){
				alert('사진을 등록하시지않으셨습니다.');
					return;
			}
			console.log(data);


			// console.log(data);

			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			$.ajax({
				type: "POST",
				url: "/crud",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",
			       // enctype: 'multipart/form-data',// body데이터가 어떤 타입인지(MIME)
				dataType: "text" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				console.log(resp);
				alert('성공');
//
					// location.href = "/list";


			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},

		start: function(){


			// alert('user의 save함수 호출됨');
			let data = {
					changebgImg : $("#imgs").val(),

			};


			$.ajax({
				 url : '/instar',
			        type : 'POST',
			        data : data,
			        contentType : false,
			        processData : false

			}).done(function(resp){
				console.log(resp);
				alert('성공');
//
					// location.href = "/list";


			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},





		// delete 하는곳
		userDelete: function(e){
			// alert('user의 save함수 호출됨');
			let temp = (e.target.id).replace("bt-delete-","");
			let data = {
					id: temp
			};
			console.log('최종 = ',data.id);
			$.ajax({
				type: "delete",
				url: "/listDelete/"+data.id,
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "text" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				alert("회원삭제가 완료되었습니다.");
				console.log(resp);
				location.href = "/list";

			}).fail(function(error){
				alert(JSON.stringify(error));
			});



		},

		update: function(e){

			console.log('update에왔음');
			console.log('e =',e);
			let temp = (e.target.id).replace("bt-update-","");
			let data2 = {
					id: temp
			};
			console.log("짜른 data2.id = ",data2.id);
			let data ={
					id:$("#modalid"+data2.id).val(),
					upPoint:$("#modalupPoint"+data2.id).val(),
					minusPoint:$("#modalminusPoint"+data2.id).val(),
					reason:$("#modalreason"+data2.id).val(),
					totalPoint:$("#modaltotalPoint"+data2.id).val(),

			}
			console.log('data의 내용들 = ',data);

			$.ajax({
				type: "PUT",
				url: "/pointUpdate/"+data.id,
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",
			       // enctype: 'multipart/form-data',// body데이터가 어떤 타입인지(MIME)
				dataType: "JSON"
			}).done(function(resp){
				alert("물품변경이 완료되었습니다.");
				// console.log(resp);
				 location.href = "/pointList";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},

		ViewDetails: function(e){

			console.log('e =',e);
			let temp = (e.target.id).replace("bt-ViewDetails-","");

			let data2 = {
					id: temp
			};
			console.log('data2 = ',data2);

			let data = {
					id:$("#id"+data2.id).val(),
					username:$("#username"+data2.id).val(),
					createDate:$("#createDate"+data2.id).val(),
					name:$("#name"+data2.id).val(),

			};
			console.log('들어온 data = ',data);
			console.log('들어온 userId = ',data.id);



			// console.log(data);

			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			$.ajax({
				type: "PUT",
				url: "/pointDetail/"+data.id,
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",
			       // enctype: 'multipart/form-data',// body데이터가 어떤 타입인지(MIME)
				dataType: "JSON" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				console.log("꺼내온 resp = ",resp);
				$("#ajax").empty();

				for(var data of resp){ //for(var data(변수명) if resp(성공한거의 끝까지나오게해라))

					var input_html =
						"<table class=\"table\">\r\n" +
						"<thead>\r\n" +
						"<tr>\r\n" +
						"<th>username</th>\r\n" +
						"<th>name</th>\r\n" +
						"<th>createDate</th>\r\n" +
						"<th>id</th>\r\n" +
						"<th>userId</th>\r\n" +
						"<th>historyDate</th>\r\n" +
						"<th>reason</th>\r\n" +
						"<th>point</th>\r\n" +
						"<th>totalPoint</th>\r\n" +
						"</thead>\r\n" +
						"<tbody>\r\n" +
						" <tr class=\"success\">\r\n" +
						"        <td>"+data.username+"</td>\r\n" +
						"        <td>"+data.name+"</td>\r\n" +
						"        <td>"+data.createDate+"</td>\r\n" +
						"        <td>"+data.id+"</td>\r\n" +
						"        <td>"+data.userId+"</td>\r\n" +
						"        <td>"+data.historyDate+"</td>\r\n" +
						"        <td>"+data.reason+"</td>\r\n" +
						"        <td>"+data.point+"</td>\r\n" +
						"        <td>"+data.userTotalPoint+"</td>\r\n" +
						"      </tr>\r\n" +
						"</tbody>\r\n" +
						"</table>";
					console.log(input_html);
					$("#ajax").append(input_html);
////				$('#ajaxtest').val(resp[0].totalPrice);
//
//				}
//
//				$(".btn-update").on("click", (e)=>{
//					console.log('e =',e)
//				console.log('왔음');
//				test1(e);
//				});
				}

//				<th>유저넘버</th>
//				<th>아이디</th>
//				<th>이름</th>
//				<th>가입일자</th>
//				<th>쿠폰번호</th>
//				<th>시작일자</th>
//				<th>종료일자</th>
//				<th>할인가격</th>
//				<th>쿠폰사유</th>

			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},
}

function test1(e){
	console.log(e);
	let temp = (e.target.id).replace("bt-update-","");//"bt-update-"+data.orders_detailId+"
	let data = {
			id: temp
	};
console.log("받아온 id값 = ",data.id)
let data2={
	stats : $("#stats"+data.id).val(),
	orders_detailId : data.id,
}
console.log("data2= ",data2);

$.ajax({
	type: "PUT",
	url: "/chaneStats/"+data.id,
	data: JSON.stringify(data2), // http body데이터
	contentType: "application/json; charset=utf-8",// body데이터가 어떤
	dataType: "text" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
						// json이라면) => javascript오브젝트로 변경
}).done(function(resp){

	console.log(resp);
	location.href="/orders"

}).fail(function(error){
	alert(JSON.stringify(error));
});


}




index.init();