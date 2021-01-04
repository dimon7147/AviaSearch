<#macro modal idModal, fieldName>
    <div class="modal" tabindex="-1" role="dialog" id="${idModal}">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Редактирование ${fieldName}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="waitingToEdit">
                    <div class="d-flex justify-content-center">
                        Идет загрузка данных ${fieldName}, пожалуйста, ожидайте...
                        <div class="spinner-border" role="status">
                            <span class="sr-only">Загрузка...</span>
                        </div>
                    </div>
                </div>
                <#nested>
            </div>
        </div>
    </div>
</#macro>
<#macro modalFooter successFunction>
    <div class="modal-footer">
        <button type="button" onclick="${successFunction}" class="btn btn-primary" data-dismiss="modal" id="saveB">Сохранить изменения</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть без сохранения</button>
    </div>
</#macro>
<#macro addmodal idModal, fieldName>
    <div class="modal" tabindex="-1" role="dialog" id="${idModal}">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Добавление ${fieldName}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <#nested>
            </div>
        </div>
    </div>
</#macro>
<#macro addmodalFooter successFunction>
    <div class="modal-footer">
        <button type="button" onclick="${successFunction}" class="btn btn-primary" data-dismiss="modal" id="addB">Добавить</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
    </div>
</#macro>
<#macro updatePanel fieldName>
    <div class="modal-body" id="tableLoad" style="display: none">
        <div class="d-flex justify-content-center">
            Идет загрузка данных ${fieldName}, пожалуйста, ожидайте...
            <div class="spinner-border" role="status">
                <span class="sr-only">Загрузка...</span>
            </div>
        </div>
    </div>
</#macro>