package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import br.com.vemser.pessoaapi.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paginacao")
@RequiredArgsConstructor
public class PaginacaoController {

    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;
    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;


    @GetMapping
    public Page<PessoaEntity> getPessoas(Integer pagina, Integer quantidadeDeRegistros){
        return pessoaService.listPaginado(pagina, quantidadeDeRegistros);
    }

    @GetMapping("/contato-por-descricao")
    public Page<ContatoEntity> contatoPorDescricao(Integer pagina, Integer quantidadeRegistros){
        Sort ordenacao = Sort.by("descricao");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
        return contatoRepository.contatoPorDescricao(pageable);
    }


    @GetMapping("/endereco-por-cep")
    public Page<EnderecoEntity> enderecoPorCep(Integer pagina, Integer quantidadeRegistros){
        Sort ordenacao = Sort.by("cep");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
//        Page<PessoaCompostaTudoDTO> pessoaCompostaTudoPaginado = pessoaRepository.findPessoaCompostaTudoPaginado(pageable);
//        return pessoaCompostaTudoPaginado.getContent();
        return enderecoRepository.enderecoPorCep(pageable);
    }

    @GetMapping("/endereco-por-pais")
    public Page<EnderecoEntity> enderecoPorPais(Integer pagina, Integer quantidadeRegistros){
        Sort ordenacao = Sort.by("pais");
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
//        Page<PessoaCompostaTudoDTO> pessoaCompostaTudoPaginado = pessoaRepository.findPessoaCompostaTudoPaginado(pageable);
//        return pessoaCompostaTudoPaginado.getContent();
        return enderecoRepository.enderecoPorPais(pageable);
    }

//    @GetMapping("/por-cpf-nativo")
//    public Page<PessoaEntity> getCpfNativo(Integer pagina, Integer quantidadeRegistros, @RequestParam(required = false) String cpf){
//        Sort ordenacao = Sort.by("nome");
//        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros, ordenacao);
//        return pessoaRepository.listPessoasByCpfNativePaginado(cpf, pageable);
//    }


}
