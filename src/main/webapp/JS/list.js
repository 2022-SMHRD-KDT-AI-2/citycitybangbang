		function loadList(){
		
		// #id
		// .class
		// 태그이름[속성명=속성값]
		// .html() => 닫는태그 있는 경우
		// .val() => 닫는태그 없는 경우
		let date = $('input[name=date]').val()
		let area = $('select[name=area]').val()
		let check = $('select[name=check]').val()
		console.log(date);
		// ajax 요청
		$.ajax({
			// /ContextPath/urlMapping
			url : '/citycitybangbang/webReport', //어디로 요청할건지
			type : 'get', //Get? Post?
			data : {
				// 보내주는 data
				// "name" : value
				"date" : date,
				"area" : area,
				"check": check
				
			},
			dataType : 'json',
			success : function(res){
				// 요청이 성공하면 실행
				up(res)
				
			},
			error : function(){alert("error!");}
		})			
	}	
	
	function up(res) {
		console.log(res)
		let tr = '';
		for(let i = 0; i<res.length; i++){
			tr += `
				 <tr name="list`+i+`">
                	<td id="tdl" class="day">`+res[i].acc_date +`</td>
                	<td id="tdl" class="list">`+res[i].acc_place+`</td>
                	<td id="tdl" class="check" >`+res[i].re_complete+`</td>
            	</tr>`
			
		}
		
		$("#listtable>tbody").html(tr)
	}