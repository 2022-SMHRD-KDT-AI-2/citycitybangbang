
google.charts.load('current', {'packages':['bar']});
//

var bar = [
        ['X년x월x일', '신고건수'],
        ['00시-02시', 5],
        ['03시-05시', 4],
        ['06시-08시', 9],
        ['09시-11시', 15],
        ['12시-14시', 20],
        ['15시-17시', 8],
        ['18시-20시', 25],
        ['21시-23시', 27]
    ]

function barChart() {
    var bardata = google.visualization.arrayToDataTable(bar);
    

        var baroptions = {
   
          chart: {
                  title: ' ',
                  titleTextStyle: {
                      color: '#000000',
                      fontSize:100
                      
                   },
                   textStyle:{
                       color: '#000000',
                       fontSize:30
                       
                       
                   },
               
                   
          },
          hAxis: {
                   title: '시간대',
                   titleTextStyle: {
                       color: '#000000',
                       fontSize:15
                      
                   },
                   textStyle:{
                       color: '#000000',
                       fontSize:15
                   }
               },
               vAxis: {
                   titleTextStyle: {
                       color: '#000000'
                   },
                   textStyle:{
                       color: '#000000',
                       fontSize:18
                   }
               },
               legend: {
                   textStyle: {
                       color: '#000000',
                       fontSize:15
                  }
            },
          
        };

        var barchart = new google.charts.Bar(document.getElementById('columnchart_material'));

        barchart.draw(bardata, google.charts.Bar.convertOptions(baroptions));
        
       
      }
      
function loadBarChart(){
   google.charts.setOnLoadCallback(barChart);
}

function reloadBar(res){
	
   let hour =[]
   let time = []
   for(let i=0;i<8;i++){
      time[i]=0;
   }
   for(let i = 0; i<res.length; i++){
      let arr = res[i].acc_date.split(/:| /);
      arr[1]*=1;
      hour[i]=arr[1];
   }
   
   
   
   for(let i = 0; i<res.length; i++){
      if(hour[i]>=0 && hour[i]<=2){
         time[0] +=1;
      }else if(hour[i]>=3 && hour[i]<=5){
         time[1] +=1;
      }else if(hour[i]>=6 && hour[i]<=8){
         time[2] +=1;
      }else if(hour[i]>=9 && hour[i]<=11){
         time[3] +=1;
      }else if(hour[i]>=12 && hour[i]<=14){
         time[4] +=1;
      }else if(hour[i]>=15 && hour[i]<=17){
         time[5] +=1;
      }else if(hour[i]>=18 && hour[i]<=20){
         time[6] +=1;
      }else{
         time[7] +=1;
      }
      for(let i = 0; i<time.length; i++){
         bar[i+1][1]=time[i]
      }
      
   }   
   google.charts.setOnLoadCallback(barChart)
}