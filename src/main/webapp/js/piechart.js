google.load("visualization", "1", {packages:["corechart"]}); 
google.setOnLoadCallback(pieChart); 
	function pieChart() { 
    var piedata = google.visualization.arrayToDataTable( 
        [
            ["Employee","Rating"],
            ["처리완료",27],
            ["미처리",27],
            ["기타",17],
        ] ); 
    var pieoptions = { title: "일별 처리율 통계" }; 
    var piechart = new google.visualization.PieChart(document.getElementById("employee_piechart")); 
    piechart.draw(piedata, pieoptions); } 
