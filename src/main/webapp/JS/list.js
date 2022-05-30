		function loadList(){
		
		// #id
		// .class
		// 태그이름[속성명=속성값]
		// .html() => 닫는태그 있는 경우
		// .val() => 닫는태그 없는 경우
		let date = $('input[name=date]').val()
		let area = $('select[name=area]').val()
		let check = $('select[name=check]').val()
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
		let name = [];
		let place = [];
		let img = [];
		let check=[];
		let num=[];
		let tr = '';
		let dmg=[]
		for(let i = 0; i<res.length; i++){
			name.push(res[i].mem_id);
			place.push(res[i].acc_place)
			img.push(res[i].image_file);
			check.push(res[i].re_complete)
			num.push(res[i].re_num)
			dmg.push(res[i].damage)
			

			tr += `
				 <tr>
                	<td id="tdl" class="day">`+res[i].acc_date +`</td>
                	<td id="tdl" class="list">`+place[i]+`</td>
                	<td id="tdl" class="check" >`+check[i]+`</td>
                	<td id="tdl" class="btn"><input type="button" class="checkBtn" value="up"/></td>
                	<input type="hidden" value="`+ name[i] +`"/>
                	<input type="hidden" value="`+ img[i] +`"/>
                	<input type="hidden" value="`+ num[i] +`"/>  
                	<input type="hidden" value="`+ dmg[i] +`"/>                 	
            	</tr>`
			
		}
		
		$("#listtable>tbody").html(tr)
	}
	

	
	
	$(document).on("click",".checkBtn",function() {

			
			var str = ""
			var checkData = $(this);
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkData.parent().parent();
			var td = tr.children();
			
			
			var id = td.eq(4).val();
			var area = td.eq(1).text();
			var img = td.eq(5).val();
			var check = td.eq(2).text();
			var num = td.eq(6).val();
			var updmg = td.eq(7).val();
			var img2 = "http://125.136.66.65:8081/report/upload/"+img
			
				function select(check) {
					if(check == "Y"){
						
					}
		
	}
                   					
			console.log(updmg)
			str +=`
			<thead>
            <tr>
                <td id="tdl" class="reporter">신고자 : "`+id+`"</td>
                <td id="tdl" style="text-align: center;">훼손 여부</td>
                <td id="tdl" class="lctlist" >처리여부</td>
                <input type="hidden" name="renum" value="`+ num +`"/>
                <input type="hidden" name="recheck" value="`+ check+`"/>
            </tr>
            <tr>
            	<td id="tdl" class="reporter" name="num" >신고 번호 : `+num+`</td>                
                <td id="tdl" class="lctlist">`+updmg+`</td>
                <td id="tdl"><select name="selectCheck"class="lctlist" style="margin-left:3px;" onchange="checkreup()">                
					<option value="처리">처리</option>
                    <option value="미처리">미처리</option></td>
                    
            </tr>
             <tr>
				<td id="tdl" class="reporter" colspan="3" >신고 위치 : `+area+` </td>
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
                    <img src="`+img2+`" class="rptimg" alt="이미지">
                </td>
            </tr>
            </tbody>
			`;		
			
			$("#detailtable").html(str)
			loadList() 
				
	});


function checkreup() {
		let check = $('select[name=selectCheck]').val()
		console.log(check)
		let num = $('input[name=renum]').val()
		console.log(num)
		$.ajax({
			// /ContextPath/urlMapping
			url : '/citycitybangbang/webcheckupdate', //어디로 요청할건지
			type : 'post', //Get? Post?
			data : {
				
				"recheck": check,
				"renum" : num
				
			},			
			success : function(){ 
				console.log('성공')
				loadList() 				
			},
			error : function(){alert("error!");}
		})
}	