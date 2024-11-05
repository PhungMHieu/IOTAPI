//(function() {
//    // Mã JavaScript trong script.js
//     // Dữ liệu cho biểu đồ
//     const labels = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12];
//     const temperatureData = [30, 32, 35, 37, 40, 42, 45, 44, 40, 38, 34, 32];
//     const humidityData = [70, 65, 60, 55, 50, 45, 40, 42, 48, 55, 65, 70];
// 
//     // Tạo biểu đồ
//     const ctx = document.getElementById('tHChart').getContext('2d');
//     const myChart = new Chart(ctx, {
//         type: 'line',
//         data: {
//             labels: labels,
//             datasets: [
//                 {
//                     label: 'Nhiệt độ (°C)',
//                     data: temperatureData,
//                     borderColor: 'rgba(255, 99, 132, 1)',
//                     backgroundColor: 'rgba(255, 99, 132, 0.2)',
//                     fill: false
//                 },
//                 {
//                     label: 'Độ ẩm (%)',
//                     data: humidityData,
//                     borderColor: 'rgba(54, 162, 235, 1)',
//                     backgroundColor: 'rgba(54, 162, 235, 0.2)',
//                     fill: false
//                 }
//             ]
//         },
//         options: {
//             scales: {
//                 y: {
//                     beginAtZero: true,
//                     title: {
//                         display: true,
//                         text: 'Value'
//                     }
//                 },
//                 x: {
//                     title: {
//                         display: true,
//                         // text: 'thứ tự'
//                     }
//                 }
//             }
//         }
//     }); 
//})();
//
//   
(function(){
	const ctx = document.getElementById('tHChart').getContext('2d');
	const chart = new Chart(ctx, {
	            type: 'line',
	            data: {
	                labels: [],
					datasets: [
			            {
			                label: 'Nhiệt độ (°C)',
			                data: [], // Data for temperature
			                borderColor: 'rgba(255, 99, 132, 1)', // Red line
			                backgroundColor: 'rgba(255, 99, 132, 0.2)',
			                borderWidth: 1,
	                        yAxisID: 'y', // Use the primary Y-axis
			            },
			            {
			                label: 'Độ ẩm (%)',
			                data: [], // Data for humidity
			                borderColor: 'rgba(54, 162, 235, 1)', // Blue line
			                backgroundColor: 'rgba(54, 162, 235, 0.2)',
			                borderWidth: 1,
	                        yAxisID: 'y', // Use the primary Y-axis
			            },
						{
			                label: 'Ánh sáng (%)',
			                data: [], // Data for humidity
			                borderColor: 'rgba(255, 206, 86, 1)', // Change this to your desired color (e.g., Yellow)
	                        backgroundColor: 'rgba(255, 206, 86, 0.2)', // Change this to match the border color
			                borderWidth: 1,
	                        yAxisID: 'y1', // Use the secondary Y-axis
			            },
			        ],
	            },
	            options: { 
	                responsive: true,
	                scales: {
	                    y: {
	                        type: 'linear',
	                        position: 'left',
	                        beginAtZero: true,
	                        title: {
	                            display: true,
	                            text: 'Nhiệt độ và Độ ẩm',
	                        },
	                    },
	                    y1: {
	                        type: 'linear',
	                        position: 'right',
	                        beginAtZero: true,
	                        title: {
	                            display: true,
	                            text: 'Ánh sáng',
	                        },
	                        grid: {
	                            drawOnChartArea: false, // Only want the grid lines for one axis to show up
	                        },
	                    },
	                },
	            }
	        });
async function fetchData() {
    const response = await fetch('/last20');
    const data = await response.json();
	chart.data.labels = data.map(item => item.id);
    chart.data.datasets[0].data = data.map(item => item.temp);
	chart.data.datasets[1].data = data.map(item => item.hum);
	chart.data.datasets[2].data = data.map(item => item.light);
    chart.update();
}
fetchData();
setInterval(fetchData, 2000);
})();