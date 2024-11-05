(function(){
	const temp = document.getElementById('boardT');
	const hum = document.getElementById('boardH');
	const light = document.getElementById('boardL');
	async function fetchData() {
	    const response = await fetch('/last');
	    const data = await response.json();
		temp.innerHTML = `<span>${data.map(item => item.temp)}°C</span>`;
      	hum.innerHTML = `<span>${data.map(item => item.hum)}%</span>`;
      	light.innerHTML = `<span>${data.map(item => item.light)} lux</span>`;
	}
	fetchData();
	setInterval(fetchData, 2000);
})();