package br.com.vemser.pessoaapi.dto.Pet;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageDTO<T> {
    private Long totalElements;
    private Integer totalPages;
    private Integer page;
    private Integer size;
    private List<T> content;
}