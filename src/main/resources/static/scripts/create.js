document.addEventListener("DOMContentLoaded", function() {
    var dateField = $("#registrationDate")[0];

    console.log(dateField)
    function getCurrentDate() {
        var today = new Date();
        var year = today.getFullYear();
        var month = (today.getMonth() + 1).toString().padStart(2, '0');
        var day = today.getDate().toString().padStart(2, '0');
        return [year, month, day].join('-');
    }
    dateField.max = getCurrentDate();
});