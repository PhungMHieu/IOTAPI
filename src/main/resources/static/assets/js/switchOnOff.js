//function setupSwitch(switchId, IdSwitched) {
//    const switchElement = document.getElementById(switchId);
//    switchElement.addEventListener('change', function() {
//        if (switchElement.checked) {
//            console.log("Switch is ON");
//            const ele = document.getElementById(IdSwitched)
//            ele.classList.remove('bg-gradient-secondary')
//            ele.classList.add('bg-gradient-success');
//        } else {
//            console.log("Switch is Off");
//            const ele = document.getElementById(IdSwitched)
//            ele.classList.remove('bg-gradient-success')
//            ele.classList.add('bg-gradient-secondary');
//        }
//    });
//}
//setupSwitch('fan','d1');
//setupSwitch('air_conditioner','d2')
//setupSwitch('lamp','d3')
function setupSwitch(switchId, IdSwitched) {
    const switchElement = document.getElementById(switchId);
    const ele = document.getElementById(IdSwitched);

    // Khôi phục trạng thái từ local storage
    const storedState = localStorage.getItem(switchId);
    if (storedState === 'true') {
        switchElement.checked = true;
        ele.classList.remove('bg-gradient-secondary');
        ele.classList.add('bg-gradient-success');
        console.log("Switch is ON (restored)");
    } else {
        switchElement.checked = false;
        ele.classList.remove('bg-gradient-success');
        ele.classList.add('bg-gradient-secondary');
        console.log("Switch is OFF (restored)");
    }

    switchElement.addEventListener('change', function() {
        if (switchElement.checked) {
            console.log("Switch is ON");
            ele.classList.remove('bg-gradient-secondary');
            ele.classList.add('bg-gradient-success');
            // Lưu trạng thái vào local storage
            localStorage.setItem(switchId, 'true');
        } else {
            console.log("Switch is OFF");
            ele.classList.remove('bg-gradient-success');
            ele.classList.add('bg-gradient-secondary');
            // Lưu trạng thái vào local storage
            localStorage.setItem(switchId, 'false');
        }
    });
}

setupSwitch('fan', 'd1');
setupSwitch('air_conditioner', 'd2');
setupSwitch('lamp', 'd3');