var base_url = location.protocol + "//" + location.host + "/anonymous/";
function ajax(type, action, data, success, fail) {
    $.ajax({
        url: base_url + action,
        type: type,
        data: data,
        beforeSend: function () {
            $('.loading').show();
        },
        success: success,
        error: fail,
        complete: function () {
            $('.loading').hide();
        }
    })
}
function restorePasswordAjax(form) {

    var data = {
        email: form.email.value,
        "g-recaptcha-response": $('#g-recaptcha-response-1').val()
    };

    function success(response) {
        $('#restore-password-modal').html(response);
        if (window.grecaptcha2 != null) {
            window.grecaptcha2.reset();
        }
    }

    function fail(response) {
        alert(response.responseText);
        if (window.grecaptcha2 != null) {
            window.grecaptcha2.reset();
        }
    }

    ajax(form.method, "restore-password", data, success, fail);
}
