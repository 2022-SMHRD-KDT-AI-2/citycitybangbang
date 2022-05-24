google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(pieChart);
      function pieChart() {
        var piedata = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['처리',  11],
          ['미처리', 30],
        ]);

        var pieoptions = {
          title: '일별 신고 처리수',
          pieHole: 0.4,
          colors: ['#50bcdf','#FF3533']
          
        };

        var piechart = new google.visualization.PieChart(document.getElementById('donutchart'));
        piechart.draw(piedata, pieoptions);
      }