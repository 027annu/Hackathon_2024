$(document).ready(function() {
    // Initialize DataTable
    $('#myDataTable').DataTable({
        paging: true, // Enable pagination
        searching: true, // Enable search
        ordering: true, // Enable sorting
        info: true, // Show information
        lengthChange: false, // Disable the "Show X entries" dropdown
    });
});