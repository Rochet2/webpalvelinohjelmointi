
var url = contextRoot + "tasks"

{
    var http1 = new XMLHttpRequest()

    http1.onreadystatechange = function () {
        if (this.readyState != 4 || this.status != 200) {
            return
        }

        var tasks = document.getElementById("tasks")
        while (tasks.firstChild) {
            tasks.removeChild(tasks.firstChild);
        }
        response = JSON.parse(this.responseText)
        response.map(e => {
            var li = document.createElement("li")
            li.appendChild(document.createTextNode(e.name))
            return li
        }).forEach(e => tasks.appendChild(e))
    }

    function getTasks() {
        http1.open("GET", url)
        http1.send()
    }
    getTasks()
}

{
    var http2 = new XMLHttpRequest()

    http2.onreadystatechange = function () {
        if (this.readyState != 4) {
            return
        }

        var li = document.createElement("li")
        li.appendChild(document.createTextNode(JSON.parse(this.responseText).name))
        document.getElementById("tasks")
                .appendChild(li)
    }

    function send() {
        var data = {
            name: document.getElementById("name").value
        }

        if (data.name.length === 0)
            return

        http2.open("POST", url)
        http2.setRequestHeader("Content-Type", "application/json");
        http2.send(JSON.stringify(data))
        document.getElementById("name").value = ""
    }
}