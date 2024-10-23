package com.ghostappi.backend.service;

import java.util.List;
import java.util.Optional;

//import com.ghostappi.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Sale;
import com.ghostappi.backend.repository.SaleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public void save(Sale sale) {
        saleRepository.save(sale);
    }
    public List<Sale> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Sale> sale = saleRepository.findAll(pageReq);
        return sale.getContent();
    }
    public Sale getByIdSale(Integer idSale) {
        Optional<Sale> optionalSale = saleRepository.findById(idSale);
        if (optionalSale.isPresent()) {
            return optionalSale.get();
        } else {
            throw new IllegalArgumentException("Sale not found with id: " + idSale);
        }
    }

    public void deleteById(Integer idSale) {
        saleRepository.deleteById(idSale);
    }
}
