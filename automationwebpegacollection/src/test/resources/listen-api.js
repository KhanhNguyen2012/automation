(function() {
    const OriginalXMLHttpRequest = XMLHttpRequest;
    class CustomXMLHttpRequest {
        constructor() {
            this.xhr = new OriginalXMLHttpRequest();
            this.headers = {};
            this.url = '';
            this._setupInterceptors();
        }

        _setupInterceptors() {
            this.xhr.onreadystatechange = () => {
                if (this.xhr.readyState === 4) {
                    console.log('[listen-api][url]: ', this.url);
                    if (this.url.includes('%s')) {
                        localStorage.setItem('urlAPI', this.url);
                    }
                }
            };
        }

        open(method, url, async = true, user = null, password = null) {
            this.method = method;
            this.url = url;
            this.xhr.open(method, url, async, user, password);
        }

        setRequestHeader(key, value) {
            this.headers[key] = value;
            this.xhr.setRequestHeader(key, value);
        }

        send(body = null) {
            this.xhr.send(body);
        }

        addEventListener(type, listener) {
            this.xhr.addEventListener(type, listener);
        }

        removeEventListener(type, listener) {
            this.xhr.removeEventListener(type, listener);
        }

        get responseText() {
            return this.xhr.responseText;
        }

        get status() {
            return this.xhr.status;
        }
    }
    window.XMLHttpRequest = CustomXMLHttpRequest;
})();