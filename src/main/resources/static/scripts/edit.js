document.addEventListener("DOMContentLoaded", function() {
    function convertDateFromDDMMYYYY(dateStr) {
        var parts = dateStr.split('/');
        if (parts.length === 3) {
            var day = parts[0].padStart(2, '0');
            var month = parts[1].padStart(2, '0');
            var year = parts[2];
            return [year, month, day].join('-');
        }
        return '';
    }

    var dateField = $("#registrationDate")[0];
    var initialValue = dateField.defaultValue;

    var formattedValue = '';
    if (initialValue.includes('/')) {
        formattedValue = convertDateFromDDMMYYYY(initialValue);
    } else {
        formattedValue = initialValue;
    }

    dateField.value = formattedValue;
});

document.addEventListener("DOMContentLoaded", function() {
    var dateField = $("#registrationDate")[0];

    function getCurrentDate() {
        var today = new Date();
        var year = today.getFullYear();
        var month = (today.getMonth() + 1).toString().padStart(2, '0');
        var day = today.getDate().toString().padStart(2, '0');
        return [year, month, day].join('-');
    }

    dateField.max = getCurrentDate();
});