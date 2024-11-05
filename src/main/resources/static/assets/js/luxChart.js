(function() {
    // Dữ liệu mẫu
   const data = {
     labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], // Nhãn cho các điểm dữ liệu
     datasets: [{
       label: 'Độ rọi (Lux)',
       data: [200, 400, 600, 800, 1000, 700,800,800,900,200,100,200,300,400,100], // Giá trị độ rọi
       borderColor: 'rgba(75, 192, 192, 1)',
       backgroundColor: 'rgba(75, 192, 192, 0.2)',
       borderWidth: 2,
       fill: true
     }]
   };
 
   // Tạo biểu đồ
   const ctx = document.getElementById('luxChart').getContext('2d');
   const luxChart = new Chart(ctx, {
     type: 'line',
     data: data,
     options: {
       scales: {
         y: {
           beginAtZero: true,
           max: 1000 // Đặt giới hạn tối đa cho trục y
         }
       }
     }
   });
 
 })();
   