const body = document.querySelector("body"), modeToggle = body.querySelector(".mode-toggle");
sidebar = body.querySelector("nav");
sidebarToggle = body.querySelector(".sidebar-toggle");

let getMode = localStorage.getItem("mode");
if (getMode && getMode === "dark") {
    body.classList.toggle("dark");
}

let getStatus = localStorage.getItem("status");
if (getStatus && getStatus === "close") {
    sidebar.classList.toggle("close");
}

modeToggle.addEventListener("click", () => {
    body.classList.toggle("dark");
    if (body.classList.contains("dark")) {
        localStorage.setItem("mode", "dark");
    } else {
        localStorage.setItem("mode", "light");
    }
});

sidebarToggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
    if (sidebar.classList.contains("close")) {
        localStorage.setItem("status", "close");
    } else {
        localStorage.setItem("status", "open");
    }
});

function goToDashboard() {
    fetch('/admin/view/dashboard', {
        method: 'get',
        headers: {
            'authorization': localStorage.getItem("accessToken")
        }
    })
}

function goToUsers() {
    fetch('/admin/view/users', {
        method: 'get',
        headers: {
            'authorization': localStorage.getItem("accessToken")
        }
    })
}

function goToPrompts() {
    fetch('/admin/view/prompts', {
        method: 'get',
        headers: {
            'authorization': localStorage.getItem("accessToken")
        }
    })
}

function goToPromptTypes() {
    fetch('/admin/view/prompt-types', {
        method: 'get',
        headers: {
            'authorization': localStorage.getItem("accessToken")
        }
    })
}

function goToAdmins() {
    fetch('/admin/view/admins', {
        method: 'get',
        headers: {
            'authorization': localStorage.getItem("accessToken")
        }
    })
}