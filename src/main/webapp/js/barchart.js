google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(barChart);

function barChart() {
    var bardata = google.visualization.arrayToDataTable([
        ['X년x월x일', '신고건수'],
        ['00시-03시', 5],
        ['03시-06시', 4],
        ['06시-09시', 9],
        ['09시-12시', 15],
        ['12시-15시', 20],
        ['15시-18시', 8],
        ['18시-21시', 25],
        ['21시-00시', 27]
    ]);

        var baroptions = {
          chart: {
            title: '시간대 별 신고량',
            
          }
        };

        var barchart = new google.charts.Bar(document.getElementById('columnchart_material'));

        barchart.draw(bardata, google.charts.Bar.convertOptions(baroptions));
      }
