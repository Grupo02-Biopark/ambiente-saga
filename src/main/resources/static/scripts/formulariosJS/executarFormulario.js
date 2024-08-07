document.addEventListener('DOMContentLoaded', function() {
    const perguntasContainer = document.querySelector('.listarPerguntas');

    function initializePergunta(pergunta) {
        const titulo = pergunta.querySelector('.tituloPergunta a');
        const container = pergunta.querySelector('.perguntaContainer');
        const tituloTexto = pergunta.querySelector('.tituloPergunta h3');
        const inputResposta = pergunta.querySelector('input[type="hidden"]');

        // Salva o título original no dataset do elemento
        tituloTexto.dataset.originalTitle = tituloTexto.textContent;

        // Adiciona evento de clique para expandir/recolher
        titulo.addEventListener('click', function(event) {
            event.preventDefault();
            const expandido = pergunta.classList.contains('expandido');

            // Fecha todas as outras perguntas
            document.querySelectorAll('.listaPergunta').forEach(p => {
                const pTituloTexto = p.querySelector('.tituloPergunta h3');
                const pContainer = p.querySelector('.perguntaContainer');
                const pCheckboxes = p.querySelectorAll('input[type="radio"]');
                const pAlgumMarcado = Array.from(pCheckboxes).some(checkbox => checkbox.checked);

                p.classList.remove('expandido');
                pContainer.style.display = 'none';

                if (pAlgumMarcado) {
                    pTituloTexto.textContent = 'RESPONDIDO';
                    pTituloTexto.style.color = '#35B02A';
                } else {
                    pTituloTexto.textContent = pTituloTexto.dataset.originalTitle;
                    pTituloTexto.style.color = '';
                }
            });

            // Se não estava expandido antes, expande e recolhe
            if (!expandido) {
                pergunta.classList.add('expandido');
                container.style.display = 'flex';
                tituloTexto.textContent = tituloTexto.dataset.originalTitle;
                tituloTexto.style.color = '';
            } else {
                // Verifica se há resposta marcada
                const checkboxes = container.querySelectorAll('input[type="radio"]');
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
                // Remove a classe 'checked' de todos os checkmarks
                checkboxes.forEach(box => {
                    const checkmark = box.nextElementSibling;
                    if (box !== this) {
                        box.checked = false;
                        checkmark.classList.remove('checked');
                    }
                });

                if (this.checked) {
                    // Adiciona a classe 'checked' ao checkmark do checkbox marcado
                    this.nextElementSibling.classList.add('checked');
                    // Atualiza o valor do input hidden com base na seleção
                    inputResposta.value = this.value;
                } else {
                    // Remove a classe 'checked' se o checkbox for desmarcado
                    this.nextElementSibling.classList.remove('checked');
                    inputResposta.value = ''; // Limpa o valor se desmarcado
                }
            });
        });

    }

    // Inicializar todas as perguntas da página
    const perguntas = document.querySelectorAll('.listaPergunta');
    perguntas.forEach(pergunta => initializePergunta(pergunta));
});
