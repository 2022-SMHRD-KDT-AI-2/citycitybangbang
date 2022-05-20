google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(pieChart);
      function pieChart() {
        var piedata = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day', { role: 'style' }],
          ['처리',  11, 'color: #e5e4e2'],
          ['미처리', 7, 'gold'],
          ['기타', 2, 'gold']
        ]);

        var pieoptions = {
          title: '일별 신고 처리수',
          pieHole: 0.4,
        };

        var piechart = new google.visualization.PieChart(document.getElementById('donutchart'));
        piechart.draw(piedata, pieoptions);
      }