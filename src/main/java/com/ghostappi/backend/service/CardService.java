package com.ghostappi.backend.service;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.ghostappi.backend.model.Card;
	import com.ghostappi.backend.repository.CardRepository;
	import jakarta.transaction.Transactional;

@Service
@Transactional
public class CardService  {
  @Autowired
  private CardRepository wallpor;

  public List<Card> getAll(){
    return wallpor.findAll();
  }

  public void save(Card car){
    wallpor.save(car);
  }

  public Card getIdCard(Integer idCard){
    return wallpor.findById(idCard).get();
  }

  public void delete(Integer idCard){
    wallpor.deleteById(idCard);
  }
}

