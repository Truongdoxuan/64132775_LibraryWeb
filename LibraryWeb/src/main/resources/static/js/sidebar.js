function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const main = document.querySelector(".main");
    sidebar.classList.toggle('hidden');

    // Chuyển class tương ứng cho .main
    if (sidebar.classList.contains("hidden")) {
        main.classList.remove("sidebar-expanded");
        main.classList.add("sidebar-collapsed");
    } else {
        main.classList.remove("sidebar-collapsed");
        main.classList.add("sidebar-expanded");
    }
}