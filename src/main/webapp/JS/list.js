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
		let name = [];
		let place = [];
		let img = [];
		let check=[];
		let tr = '';
		for(let i = 0; i<res.length; i++){
			name.push(res[i].mem_id);
			place.push(res[i].acc_place)
			img.push(res[i].image_file);
			check.push(res[i].re_complete)
			tr += `
				 <tr>
                	<td id="tdl" class="day">`+res[i].acc_date +`</td>
                	<td id="tdl" class="list">`+place[i]+`</td>
                	<td id="tdl" class="check" >`+check[i]+`</td>
                	<td id="tdl" class="btn"><input type="button" class="checkBtn" value="up" />
                	<input type="hidden" value="`+ name[i] +`"/>
                	<input type="hidden" value="`+ img[i] +`"/></td>
                	console.log(img[i])                	
            	</tr>`
			
		}
		
		$("#listtable>tbody").html(tr)
	}
	

$(".checkBtn").click(function(){ 
			
			var str = ""
			var checkBtn = $(this);
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			var id = td.eq(4).val();
			var area = td.eq(1).text();
			var img = td.eq(5).val();
			var check = td.text(2).text;
						
			console.log(id);
			console.log(area);
			console.log(img);
			console.log(check);
			
			str +=`
			<thead>
            <tr>
                <td id="tdl" class="reporter">신고자 : "`+id+`"</td>
                <td id="tdl" style="text-align: center;">처리 여부</td>
                <td id="tdl" class="lctlist" >훼손 여부</td>
            </tr>
            <tr>
                <td id="tdl" class="reporter">신고 위치 : `+area+`</td>
                <td id="tdl" class="lctlist"> 심각함</td>
                <td id="tdl"><select name="처리 여부"class="lctlist" style="margin-left:3px;" onchange="checkreup">
                	if(check == "Y"){
						<option value="처리">처리</option>
                    	<option value="미처리">미처리</option></td>
						}esls {
						<option value="미처리">미처리</option>
                    	<option value="처리">처리</option></td>
						}
                   
            </tr>
            <tr>
                <td id="tdl" colspan="3" style="text-align: center;">
                    첨부 사진
                </td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td id="tdl" colspan="3">
                    <img src="`+img+`" class="rptimg" alt="이미지">
                </td>
            </tr>
            </tbody>
			`;		
			
			$("#detailtable").html(str)	
		});