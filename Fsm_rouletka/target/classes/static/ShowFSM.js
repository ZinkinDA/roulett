$(document).ready(function () {
    showFsm();
    showResult()
    setTimeout(showFsm,4100)
    setTimeout(showFsm,5100);
    setTimeout(showFsm,6100);

});

function showFsm() {
    $.get("/api/fsm/", function (fsm) {
        console.log(fsm);
        let html = '<div class="col-sm">'+
            '<label class="align-middle" th:align="center">UserInputValue :</label>'+
            '<label class="align-middle">' + fsm.userValue + '</label>' +
            '</div>' +
            '<div class="col-sm">' +
                '<label class="align-middle" th:align="center">Value :</label>'+
                '<label class="align-middle">' + fsm.value + '</label>'+
            '</div>' +
            '<div class="col-sm">' +
            '<label class="align-middle" th:align="center">Statement :</label>'+
            '<label class="align-middle">' + fsm.states + '</label>'+
            '</div>';


        $('#FsmUnit').html(html);
    });
}

function showResult(){
    $.get("/api/fsm/isWin", function (res) {
        let html = '<div class="implementation-label">' +
            '<label class="align-middle" th:align="center">Is win :</label>'+
            '<label class="align-middle">' + res + '</label>' +
            '</div>';

        $('#IsWin').html(html);
    });
}

function getStep() {
    let value = document.getElementById('id_ui_front').value;

    $.ajax({
        url : '/api/fsm/step',
        dataType: 'json',
        contentType : 'application/json',
        type : 'POST',
        cache : false,
        data : JSON.stringify(value),
    });

}
