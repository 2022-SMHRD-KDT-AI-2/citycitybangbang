google.charts.load("current", {packages:["corechart"]});

var data = [
      ['Task', 'Hours per Day'],
      ['처리',  12],
      ['미처리', 12],
    ]
    


  function pieChart() {
    var piedata = google.visualization.arrayToDataTable(data);

    var pieoptions = {
      title: ' ',
      pieHole: 0.4,
      colors: ['#4285F4','#FE4444'],
      width:1000,
      height:800
      
    };

    var piechart = new google.visualization.PieChart(document.getElementById('donutchart'));
    piechart.draw(piedata, pieoptions);
  }
  
  
  function loadPieChart(){
   google.charts.setOnLoadCallback(pieChart);
}

function reloadPie(res){
   let no=0;
   let yes=0;
   for(let i = 0; i < res.length; i++){
      let check = res[i].re_complete;
      if(check == 'N'){
         no += 1;
      }else{
         yes += 1;
      }
      data[1][1]=yes;
      data[2][1]=no;            
   }
   google.charts.setOnLoadCallback(pieChart)
}

