document.addEventListener('DOMContentLoaded', function() {
    const perguntasContainer = document.querySelector('.listarPerguntas');

    function initializePergunta(pergunta) {
        const titulo = pergunta.querySelector('.tituloPergunta a');
        const container = pergunta.querySelector('.perguntaContainer');
        const tituloTexto = pergunta.querySelector('.tituloPergunta h3');
        const tituloOriginal = tituloTexto.textContent;
        const inputResposta = pergunta.querySelector('input[type="hidden"][tid="respostaPergunta"]');

        // Salvar o título original no dataset do elemento
        tituloTexto.dataset.originalTitle = tituloOriginal;

        // Adiciona evento de clique para expandir/recolher
        titulo.addEventListener('click', function(event) {
            event.preventDefault();
            const expandido = pergunta.classList.contains('expandir');

            // Fecha todas as outras perguntas
            document.querySelectorAll('.listaPergunta').forEach(p => {
                const pTituloTexto = p.querySelector('.tituloPergunta h3');
                const pContainer = p.querySelector('.perguntaContainer');
                const pCheckboxes = p.querySelectorAll('input[type="checkbox"]');
                const pAlgumMarcado = Array.from(pCheckboxes).some(checkbox => checkbox.checked);

                p.classList.remove('expandir');
                pContainer.style.display = 'none';

                if (pAlgumMarcado) {
                    pTituloTexto.textContent = 'RESPONDIDO';
                    pTituloTexto.style.color = '#35B02A';
                } else {
                    pTituloTexto.textContent = pTituloTexto.dataset.originalTitle;
                    pTituloTexto.style.color = '';
                }
            });

            // Se não estava expandido antes, expandir e recolher
            if (!expandido) {
                pergunta.classList.add('expandir');
                container.style.display = 'flex';
                tituloTexto.textContent = tituloTexto.dataset.originalTitle;
                tituloTexto.style.color = '';
            } else {
                // verifica se há resposta marcada
                const checkboxes = container.querySelectorAll('input[type="checkbox"]');
                const algumMarcado = Array.from(checkboxes).some(checkbox => checkbox.checked);

                if (algumMarcado) {
                    tituloTexto.textContent = 'RESPONDIDO';
                    tituloTexto.style.color = '#35B02A';
                } else {
                    tituloTexto.textContent = tituloTexto.dataset.originalTitle;
                    tituloTexto.style.color = '';
                }
            }
        });

        // Permite que apenas um checkbox seja marcado
        const checkboxes = container.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                if (this.checked) {
                    checkboxes.forEach(box => {
                        if (box !== this) {
                            box.checked = false;
                        }
                    });
                    // Atualiza o valor do input hidden 0 = conforme, 1 = não conforme, 2 = não se aplica
                    inputResposta.value = this.dataset.resposta;
                } else {
                    inputResposta.value = '';
                }
            });
        });

        // Faz abrir o seletor de arquivos
        const documentoLink = container.querySelector('.documentoPergunta a');
        const inputDocumento = container.querySelector('.inputDocumento');
        const nomeDocumento = container.querySelector('.nomeDocumento');

        documentoLink.addEventListener('click', function(event) {
            event.preventDefault();
            inputDocumento.click();
        });

        // Substitui o texto do HTML pelo nome do arquivo
        inputDocumento.addEventListener('change', function() {
            if (inputDocumento.files.length > 0) {
                nomeDocumento.textContent = inputDocumento.files[0].name;
            } else {
                nomeDocumento.textContent = 'Nenhum arquivo selecionado';
            }
        });
    }

    // Inicializar todas as perguntas da página, para evitar bug do JS
    const perguntas = document.querySelectorAll('.listaPergunta');
    perguntas.forEach(initializePergunta);

});

