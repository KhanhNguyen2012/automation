(function() {
    const originalFn = window.sendLoginRequestForm;
    window.sendLoginRequestForm = function(event) {
        console.log("sendLoginRequestForm----------------------------------");
        return originalFn.apply(this, arguments);
    };

    const authenEmailFn = window.authenEmail;
    window.authenEmail = function(event) {
        console.log("authenEmail----------------------------------");
        return this.authenEmailFn.apply(this, arguments);
    }

    const authenQRFn = window.authenQR;
    window.authenQRFn = function(event) {
         console.log("authenQR----------------------------------");
         return this.authenQRFn.apply(this, arguments);
    }

    const form = document.querySelector("form[name='main']");
    if (!form) return;

    form.addEventListener("submit", function(event) {
//        event.preventDefault();

        const actionUrl = form.action;
        const method = form.method;

        console.log("[Url]: ", actionUrl);
        console.log("[method]: ", method);

        localStorage.setItem("url", actionUrl);

        const formData = new FormData(form);

        const params = new URLSearchParams();
        for (const [key, value] of formData.entries()) {
            params.append(key, value);
        }

//        fetch(actionUrl, {
//            method: method,
//            headers: {
//                "Content-Type": "application/x-www-form-urlencoded",
//                "Accept": "text/html"
//            },
//            body: params
//        }).then(response => {
//            if (response.status == 200) {
//                console.log("[listen-api-login][Url]: ", actionUrl);
//                console.log("[listen-api-login][Status]:", response.status);
//                localStorage.setItem("url", actionUrl);
//                localStorage.setItem("status", response.status);
//            }
//
//            return response.text();
//        }).then(body => {
//            document.open();
//            document.write(body);
//            document.close();
//        });
    });

})();