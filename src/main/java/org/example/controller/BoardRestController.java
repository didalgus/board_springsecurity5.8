package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.BoardRegRequest;
import org.example.dto.BoardResponse;
import org.example.service.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Slf4j
@Api(tags = "Board")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @ApiOperation("게시물 조회")
    @GetMapping("/board/{boardSeq}")
    public BoardResponse getBoardBySeq(@PathVariable @Valid @NotNull Long boardSeq) {
        return BoardResponse.of(boardService.getBoardBySeq(boardSeq));
    }

    @ApiOperation("게시물 등록")
    @PostMapping("/board/create")
    public String regBoard(@RequestBody @Valid BoardRegRequest boardRegRequest) {
        return boardService.regBoard(boardRegRequest);
    }

    @ApiOperation("게시물 목록 조회")
    @GetMapping("/board/list")
    public List<BoardResponse> getBoardList(@RequestParam(value = "title", required = false) String title) {
        return BoardResponse.listOf(boardService.getBoardList(title));
    }

}
