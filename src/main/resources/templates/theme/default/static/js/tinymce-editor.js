const useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
const tinymceEditor = function (save, targetId, sourceType) {
    if(!sourceType){
        sourceType='ARTICLE'
    }

    tinymce.init({
        selector: '#edit-content',
        toolbar_sticky: true,
        min_height: 600,
        auto_focus: true,
        relative_urls: false,
        plugins: 'table save lists advlist image codesample quickbars link hr autolink autoresize autosave code emoticons fullscreen help preview textpattern toc wordcount indent2em simplealert',
        toolbar: 'styleselect | lineheight indent2em bold italic strikethrough forecolor backcolor removeformat emoticons| alignleft aligncenter alignright alignjustify indent outdent | bullist numlist | table link image | hr codesample toc save restoredraft simplealert fullscreen',
        // quickbars_insert_toolbar: 'image codesample',
        quickbars_insert_toolbar: '',
        quickbars_selection_toolbar: 'bold italic | link h2 h3 blockquote copy',
        contextmenu: "bold copy link",
        link_context_toolbar: true,
        autosave_interval: "10s",
        autosave_ask_before_unload: false,
        extended_valid_elements: 'img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|uml]',
        simplealter_tinymce_url: '/static/tinymce/tinymce.min.js',

        table_class_list: [
            {title: 'None', value: ''},
            {title: 'table-hover', value: 'table table-hover'},
            {title: 'table-hover table-dark', value: 'table table-hover table-dark'},
            {title: 'table-striped', value: 'table table-striped'},
            {title: 'table-striped table-dark', value: 'table table-striped table-dark'},
            {title: 'table-bordered', value: 'table table-bordered'},
            {title: 'table-bordered table-dark', value: 'table table-bordered table-dark'},
            {title: 'table-borderless', value: 'table table-borderless'},
            {title: 'table-borderless table-dark', value: 'table table-borderless table-dark'},
            {title: 'table-sm', value: 'table table-sm'},
            {title: 'table-sm table-dark', value: 'table table-sm table-dark'},
        ],
        table_row_class_list: [
            {title: 'None', value: ''},
            {title: 'thead-dark', value: 'thead-dark'},
            {title: 'table-active', value: 'table-active'},
            {title: 'table-primary', value: 'table-primary'},
            {title: 'table-secondary', value: 'table-secondary'},
            {title: 'table-success', value: 'table-success'},
            {title: 'table-danger', value: 'table-danger'},
            {title: 'table-warning', value: 'table-warning'},
            {title: 'table-info', value: 'table-info'},
            {title: 'table-light', value: 'table-light'},
            {title: 'table-dark', value: 'table-dark'},
            {title: 'bg-primary', value: 'bg-primary'},
            {title: 'bg-success', value: 'bg-success'},
            {title: 'bg-warning', value: 'bg-warning'},
            {title: 'bg-danger', value: 'bg-danger'},
            {title: 'bg-info', value: 'bg-info'},
        ],
        // table_default_attributes: {},
        // table_default_styles: {},

        codesample_languages: [
            {text: 'Java', value: 'java'},
            {text: 'SQL', value: 'sql'},
            {text: 'JSON', value: 'json'},
            {text: 'HTML/XML', value: 'markup'},
            {text: 'Go', value: 'go'},
            {text: 'Markdown', value: 'markdown'},
            {text: 'JavaScript', value: 'javascript'},
            {text: 'CSS', value: 'css'},
            {text: 'Bash', value: 'bash'},
            {text: 'YAML', value: 'yaml'},
            {text: 'Plain Text', value: 'none'},
        ],
        content_css: 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css,/static/prism/prism.css,/theme/default/static/css/style.css',
        codesample_content_css: '/static/prism/prism.css',

        // skin: useDarkMode ? 'oxide-dark' : 'oxide',
        // content_css: useDarkMode ? 'dark' : 'default',

        default_link_target: "_blank",
        // link_list: '/api/article/view/history',
        link_list: function (success) {
            $.ajax({
                type: "GET",
                url: '/api/article/view/history',
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    const resData = data.data;
                    const links = [];
                    for (let i in resData) {
                        const d = resData[i];
                        let obj = {};
                        obj.title = d.title;
                        obj.value = "/"+d.columnPath+"/"+d.articleId;
                        links.push(obj)
                    }
                    success(links);
                }
            });


            // success([
            //     {title: 'My page 1', value: 'https://www.tiny.cloud'},
            //     {title: 'My page 2', value: 'https://about.tiny.cloud'}
            // ]);
        },
        textpattern_patterns:[
            {start: '*', end: '*', format: 'italic'},
            {start: '**', end: '**', format: 'bold'},
            {start: '#', format: 'h1'},
            {start: '##', format: 'h2'},
            {start: '###', format: 'h3'},
            {start: '####', format: 'h4'},
            {start: '#####', format: 'h5'},
            {start: '######', format: 'h6'},
            {start: '1. ', cmd: 'InsertOrderedList'},
            {start: '* ', cmd: 'InsertUnorderedList'},
            {start: '- ', cmd: 'InsertUnorderedList' },
            {start: '---', replacement: '<hr/>'},
            {start: '--', replacement: '—'},
        ],

        save_onsavecallback: function () {
            if (typeof save == 'function')
                save();
            },
//    setup: function (ed) {
//        ed.on('keydown',function(e) {
//            if(e.ctrlKey && e.keyCode === 83){
//               save();
//            }
//        });
//    },

        setup: function () {
            $(".save").click(function () {
                if (typeof save == 'function'){
                    save();
                }
            });
        },

        images_upload_handler: function (blobInfo, success, failure, progress) {
            let xhr, formData;
            xhr = new XMLHttpRequest();
            xhr.withCredentials = true;
            xhr.open('POST', '/api/file');

            xhr.upload.onprogress = function(e){
                progress(e.loaded / e.total * 100);
            }

            xhr.onload = function() {
                let json;
                if (xhr.status === 403) {
                    failure('HTTP Error: ' + xhr.status, { remove: true });
                    return;
                }
                if (xhr.status < 200 || xhr.status >= 300 ) {
                    failure('HTTP Error: ' + xhr.status);
                    return;
                }
                json = JSON.parse(xhr.responseText);
                if (!json) {
                    failure('Invalid JSON: ' + xhr.responseText);
                    return;
                }
                success("/file/image/" + json.data);
            };

            xhr.onerror = function () {
                failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
            }

            formData = new FormData();
            formData.append('targetId', targetId);
            formData.append('sourceType', sourceType);
            formData.append('file', blobInfo.blob(), blobInfo.filename());

            xhr.send(formData);
        }
    });
}

