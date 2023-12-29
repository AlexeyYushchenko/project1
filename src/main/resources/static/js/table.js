function initializeDataTable(tableId, currentLocale) {
    $(document).ready(function () {
        var languageUrl = '//cdn.datatables.net/plug-ins/1.11.5/i18n/';

        switch(currentLocale) {
            case 'ru':
                languageUrl += 'ru.json';
                break;
            case 'it':
                languageUrl += 'it-IT.json';
                break;
            case 'es':
                languageUrl += 'es-ES.json';
                break;
            case 'de':
                languageUrl += 'de-DE.json';
                break;
            // You can add more cases for other languages here
            default:
                languageUrl += 'en.json'; // Default language
        }

        $('#' + tableId).DataTable({
            "language": {
                "url": languageUrl
            }
        });
    });
}