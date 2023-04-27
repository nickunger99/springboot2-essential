package academy.devdojo.springboot2essentials.service;


import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.repository.AnimeRepository;
import academy.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import academy.devdojo.springboot2essentials.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {
   private final AnimeRepository animeRepository;

//    private static List<Anime> animes;
//    static {
//        animes = new ArrayList<>(List.of(new Anime(1L, "Boku no Hero"), new Anime(2L, "Berserk")));
//    }

    public List<Anime> ListAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
//                animes.stream()
//                .filter(anime -> anime.getId().equals(id))
//                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
      Anime anime =  Anime.builder().name(animePostRequestBody.getName()).build();
//    anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
//    animes.add(anime);
    return animeRepository.save(anime);
    }

    public void delete(long id) {
//        animes.remove(findById(id));
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
       Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime =Anime.builder().id(savedAnime.getId()).name(animePutRequestBody.getName()).build();
//         delete(anime.getId());
//         animes.add(anime);
        animeRepository.save(anime);
    }
}
