$(document).ready(function () {

    var statuses = {"0": "OFF", "1": "ON"};

    var getValue = function (element) {
        switch ($(element).prop("type")) {
            case 'checkbox':
                return $(element).prop('checked') ? 1 : 0;
            default:
                return $(element).val();
        }
    };

    var send = function (data) {
        $.post("/boiler-setup", data, function (data) {
            $("#boiler-view-settings .property-value[name='status']").text(statuses[data.status]);
            $("#boiler-view-settings .property-value[name='deltaTime']").text(data.deltaTime);
            $("#boiler-view-settings .property-value[name='deltaTemp']").text(data.deltaTemp);
        }).fail(function (data) {
            alert("Error: " + data);
        }).always(function () {
            $('#boiler-edit-dlg').dialog("close");
        });
    };

    $("#boiler-view-settings .property-value[name='status']").text(
        statuses[$("#boiler-view-settings .property-value[name='status']").text()]);

    $("#controller-state").checkboxradio();

    $('#boiler-edit-dlg').dialog({
        autoOpen: false,
        width: 350,
        buttons: {
            Cancel: function () {
                $(this).dialog("close");
            },
            Save: function () {
                var resp = {};
                $("#boiler-edit-dlg .property-value").each(function () {
                    resp[$(this).prop("name")] = getValue($(this));
                });
                send(resp);
            },
        }
    });

    $('#boiler-edit-btn').click(function () {
        var status = $("#boiler-view-settings .property-value[name='status']").text();
        var deltaTime = $("#boiler-view-settings .property-value[name='deltaTime']").text();
        var deltaTemp = $("#boiler-view-settings .property-value[name='deltaTemp']").text();

        $("#boiler-edit-dlg .property-value[name='status']")[0].checked = "ON" == status;
        $("#boiler-edit-dlg .property-value[name='deltaTime']").val(deltaTime);
        $("#boiler-edit-dlg .property-value[name='deltaTemp']").val(deltaTemp);

        $("#boiler-edit-dlg .property-value[name='status']").checkboxradio("refresh");
        $('#boiler-edit-dlg').dialog('open');
    });
});