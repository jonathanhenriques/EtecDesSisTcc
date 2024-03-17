package com.etec.tcc.sprint_quiz.api.assembler;

import com.etec.tcc.sprint_quiz.api.exception.*;
import com.etec.tcc.sprint_quiz.model.*;
import com.etec.tcc.sprint_quiz.model.dto.*;
import com.etec.tcc.sprint_quiz.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MapperAssembler {

    private final ProvaRepository provaRepository;

    private final QuestaoRepository questaoRepository;

    private final AlternativaRepository alternativaRepository;

    private final UsuarioRepository usuarioRepository;

    private final CategoriaProvaRepository categoriaProvaRepository;

    private final CategoriaQuestaoRepository categoriaQuestaoRepository;




//////////////////////PROVA///////////////////////////////////////////////////////////

    public List<ProvaDTO> converteListDeProvaParaListDeProvaDTO(List<Prova> provas){

        return provas.stream().map(p -> {
            return converteProvaParaProvaDTO(p);
        }).collect(Collectors.toList());
    }

    public ProvaDTO converteProvaParaProvaDTO(Prova prova) {


//        Prova provaRequest = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);

//        ProvaComQuestaoDTO pcdto = new ProvaComQuestaoDTO(prova.getId(), prova.getQuestoes());
//        Prova provaRequest = converteProvaComQuestaoDTOToProva(pcdto);
        Prova provaRequest = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);
        Usuario usuario = usuarioRepository.findById(provaRequest.getUsuario().getId()).orElseThrow(UsuarioNotFoundException::new);
        UsuarioSimplificadoDTO usuarioDTO = converteUsuarioParaUsuarioSimplificadoDTO(usuario);

        CategoriaProva categoriaProva = categoriaProvaRepository.findById(provaRequest.getCategoria().getId())
                .orElseThrow(CategoriaProvaNotFoundException::new);


        ProvaDTO dto = new ProvaDTO();
        dto.setId(provaRequest.getId());
        dto.setNome(provaRequest.getNome());
        dto.setDescricao(provaRequest.getDescricao());
        dto.setDuracao(provaRequest.getDuracao());
        dto.setUsuario(usuarioDTO.id());
        dto.setInstituicao(provaRequest.getInstituicao());
        dto.setCategoria(categoriaProva.getId());

        return dto;

    }

    public Prova converteProvaDTOParaProva(ProvaDTO dto) {


//        Prova provaRequest = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);

//        ProvaComQuestaoDTO pcdto = new ProvaComQuestaoDTO(prova.getId(), prova.getQuestoes());
//        Prova provaRequest = converteProvaComQuestaoDTOToProva(pcdto);
        Prova provaRequest = provaRepository.findById(dto.getId()).orElseThrow(ProvaNotFoundException::new);
        Usuario usuario = usuarioRepository.findById(provaRequest.getUsuario().getId()).orElseThrow(UsuarioNotFoundException::new);
//        UsuarioSimplificadoDTO usuarioDTO = converteUsuarioParaUsuarioSimplificadoDTO(usuario);

        CategoriaProva categoriaProva = categoriaProvaRepository.findById(provaRequest.getCategoria().getId())
                .orElseThrow(CategoriaProvaNotFoundException::new);


        Prova prova = new Prova();
        prova.setId(dto.getId());
        prova.setNome(dto.getNome());
        prova.setDescricao(dto.getDescricao());
        prova.setDuracao(dto.getDuracao());
        prova.setUsuario(usuario);
        prova.setInstituicao(provaRequest.getInstituicao());
        prova.setCategoria(categoriaProva);

        return prova;

    }

    public Prova converteProvaComQuestaoDTOToProva(ProvaComQuestaoDTO dto){
        Set<Questao> questoes = dto.questoes();

        Prova prova = provaRepository.findById(dto.id()).orElseThrow(ProvaNotFoundException::new);
        if(!prova.getQuestoes().isEmpty()){
            return colocarQuestaoEmProva(questoes, dto);
        }

        if(prova.getQuestoes().isEmpty() && !questoes.isEmpty()){
            return adicionarQuestoes(prova, questoes);
        }

        return  prova;


    }

    public Prova colocarQuestaoEmProva(Set<Questao> questoes, ProvaComQuestaoDTO dto){
        Prova provaComQuestoes = provaRepository.findAllFetch(dto.id()).orElseThrow(ProvaNotFoundException::new);
        provaComQuestoes.setQuestoes(questoes);
        return provaComQuestoes;
    }

    public Prova adicionarQuestoes(Prova prova, Set<Questao> questoes){
        prova.setQuestoes(questoes);
        return prova;
    }


    public ProvaResponse converteToProvaResponse(Prova prova) {


//        Prova provaRequest = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);

//        ProvaComQuestaoDTO pcdto = new ProvaComQuestaoDTO(prova.getId(), prova.getQuestoes());
//        Prova provaRequest = converteProvaComQuestaoDTOToProva(pcdto);
        Prova provaRequest = provaRepository.findById(prova.getId()).orElseThrow(ProvaNotFoundException::new);
        Usuario usuario = usuarioRepository.findById(provaRequest.getUsuario().getId()).orElseThrow(UsuarioNotFoundException::new);
        UsuarioSimplificadoDTO usuarioDTO = converteUsuarioParaUsuarioSimplificadoDTO(usuario);

        Set<QuestaoDTO> listaQuestoesDTO = new HashSet<>();
        List<Questao> listaDeQuestoes = new ArrayList<>(provaRequest.getQuestoes());
        if(!provaRequest.getQuestoes().isEmpty()){
            //provaQuestaoDTO

            for (int j = 0; j < provaRequest.getQuestoes().size(); j++) {
                Questao questao = questaoRepository.findById(listaDeQuestoes.get(j).getId()).orElseThrow(QuestaoNotFoundException::new);
                QuestaoDTO questaoDTO = converteQuestaoParaQuestaoDTO(questao);
                //
                questaoDTO.setIdCategoriaQuestao(questao.getCategoria().getId());

                questaoDTO.setCriadorId(questao.getCriador().getId());

                listaQuestoesDTO.add(questaoDTO);

            }
        }
        //provaQuestaoDTO



        CategoriaProva categoriaProva = categoriaProvaRepository.findById(provaRequest.getCategoria().getId())
                .orElseThrow(CategoriaProvaNotFoundException::new);


        return new ProvaResponse(
                provaRequest.getId(),
                provaRequest.getNome(),
                provaRequest.getDescricao(),
                provaRequest.getDuracao(),
                provaRequest.getInstituicao(),
                usuarioDTO,
//                provaRequest.getQuestoes(),
                listaQuestoesDTO,
                categoriaProva.getId()
        );
    }







    //////////////////////PROVA///////////////////////////////////////////////////////////


    //////////////////////////////QUESTAO/////////////////////////////////////////////////

    public List<QuestaoDTO> converteListDeQuestoesParaListDequestoesDTO(List<Questao> questoes){

        return questoes.stream().map(q -> {
            return converteQuestaoParaQuestaoDTO(q);
        }).collect(Collectors.toList());
    }


    public QuestaoDTO converteQuestaoParaQuestaoDTO(Questao questao){
        List<AlternativaDTO> alternativasDTO;
        if(!questao.getAlternativas().isEmpty()){
            alternativasDTO = converteListDeAlternativasParaListDeAlternativasDTO(questao.getAlternativas());
        }else {
            alternativasDTO = null;
        }

        AlternativaDTO alternativaDTO;
        if(questao.getResposta() != null){
            Alternativa alternativa = alternativaRepository
                    .findById(questao.getResposta())
                    .orElseThrow(AlternativaNotFoundException::new);
            alternativaDTO = converteAlternativaParaAlternativaDTO(alternativa);
        }else{
            alternativaDTO = null;
        }

        return new QuestaoDTO(
                questao.getId(),
                questao.getInstituicao(),
                questao.getImagem(),
                questao.getTexto(),
                questao.getDificuldade(),
                alternativasDTO,
                alternativaDTO,
                questao.getCategoria().getId(),
                questao.getCriador().getId()

        );

    }

    public Set<QuestaoDTO> converteSetDeQuestoesParaSetDequestoesDTO(Set<Questao> questoes){

        return questoes.stream().map(q -> {
            return converteQuestaoParaQuestaoDTO(q);
        }).collect(Collectors.toSet());
    }


    public Questao converteQuestaoComAlternativaDTOToQuestao(QuestaoComAlternativaDTO dto){
        List<Alternativa> alternativas = dto.alternativas();

        Questao questao = questaoRepository.findById(dto.id()).orElseThrow(QuestaoNotFoundException::new);
        if(!questao.getAlternativas().isEmpty()){
            return colocaAlternativasEmQuestao(alternativas, dto);
        }

        if(questao.getAlternativas().isEmpty() && !alternativas.isEmpty()) {
            return adicionarAlternativasAQuestao(questao, alternativas);
        }

        return questao;
    }


    public Questao colocaAlternativasEmQuestao(List<Alternativa> alternativas, QuestaoComAlternativaDTO dto){
        Questao questaoComAlternativas = questaoRepository.findAllFetch(dto.id()).orElseThrow(QuestaoNotFoundException::new);
        questaoComAlternativas.setAlternativas(alternativas);
        return questaoComAlternativas;
    }

    public Questao adicionarAlternativasAQuestao(Questao questao, List<Alternativa> alternativas){
        questao.setAlternativas(alternativas);
        return questao;
    }







    //////////////////////////////QUESTAO/////////////////////////////////////////////////

    /////////////////////////////////ALTERNATIVA//////////////////////////////////////////


//    public List<AlternativaDTO> converteListDeAlternativasParaListDeAlternativasDTO(List<Alternativa> alternativas){
//
//        return alternativas
//                .stream()
//                .map(this::converteAlternativaParaAlternativaDTO).collect(Collectors.toList());
//    }

    public List<AlternativaDTO> converteListDeAlternativasParaListDeAlternativasDTO(List<Alternativa> alternativas){

        return alternativas
                .stream()
                .map(a -> converteAlternativaParaAlternativaDTO(a)).collect(Collectors.toList());
    }

    public AlternativaDTO converteAlternativaParaAlternativaDTO(Alternativa alternativa){
        alternativaRepository.findById(alternativa.getId());
        AlternativaDTO dto = new AlternativaDTO();
        dto.setId(alternativa.getId());
        dto.setFoto(alternativa.getFoto());
        dto.setTexto(alternativa.getTexto());
        return dto;
    }









    /////////////////////////////////ALTERNATIVA//////////////////////////////////////////

    /////////////////////////////////USUARIO////////////////////////////////////////////

    public UsuarioSimplificadoDTO converteUsuarioParaUsuarioSimplificadoDTO(Usuario usuario) {


        return new UsuarioSimplificadoDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getUsername());
    }








    /////////////////////////////////USUARIO////////////////////////////////////////////

    //////////////////////////////////CATEOGIRAPROVA///////////////////////////////////

    public List<CategoriaProvaDTO> converteListaCategoriaProvaParaListaCategoriaProvaDTO
            (List<CategoriaProva> listaCategoriaProva){

        return listaCategoriaProva.stream().map(categoriaProva -> {
           CategoriaProvaDTO dto = new CategoriaProvaDTO();
           dto.setId(categoriaProva.getId());
           dto.setTitulo(categoriaProva.getTitulo());
           dto.setDescricao(categoriaProva.getDescricao());
           return dto;

       }).collect(Collectors.toList());
    }

    public CategoriaProvaDTO converteCategoriaProvaParaCategoriaProvaDTO(CategoriaProva categoriaProva){
        CategoriaProvaDTO dto = new CategoriaProvaDTO();
        dto.setId(categoriaProva.getId());
        dto.setTitulo(categoriaProva.getTitulo());
        dto.setDescricao(categoriaProva.getDescricao());
        return dto;
    }

    public CategoriaProva converteCategoriaProvaDTOParaCategoriaProva(CategoriaProvaDTO categoriaProvaDTO){
        CategoriaProva categoriaProva = new CategoriaProva();
        categoriaProva.setId(categoriaProvaDTO.getId());
        categoriaProva.setTitulo(categoriaProvaDTO.getTitulo());
        categoriaProva.setDescricao(categoriaProvaDTO.getDescricao());
        return categoriaProva;
    }

    public CategoriaProva converteCategoriaProvaComProvasDTOParaCategoriaProva(CategoriaProvaComProvasDTO dto){
        CategoriaProva categoriaProva = new CategoriaProva();
        categoriaProva.setId(dto.getId());
        categoriaProva.setTitulo(dto.getTitulo());
        categoriaProva.setDescricao(dto.getDescricao());

        if(!Objects.isNull(dto.getProvas())) {
            List<Prova> provas = dto.getProvas().stream().map(this::converteProvaDTOParaProva).toList();
            categoriaProva.setProvas(provas);
        }
        return categoriaProva;
    }

    public CategoriaProvaComProvasDTO converteCategoriaProvaParaCategoriaProvaComProvasDTO(CategoriaProva categoria){
        CategoriaProvaComProvasDTO dto = new CategoriaProvaComProvasDTO();
        dto.setId(categoria.getId());
        dto.setTitulo(categoria.getTitulo());
        dto.setDescricao(categoria.getDescricao());
        if(!Objects.isNull(categoria.getProvas())) {
            List<ProvaDTO> provasDTO = converteListDeProvaParaListDeProvaDTO(categoria.getProvas());
            dto.setProvas(provasDTO);
        } else
            dto.setProvas(null);
        return dto;
    }



    //////////////////////////////////CATEOGIRAPROVA///////////////////////////////////

}
