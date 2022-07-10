package com.moneyAppV5.cart.dto;

import java.util.List;

public class ShoppingListWrapperDTO
{
    List<ShoppingPositionDTO> positions;

    public List<ShoppingPositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<ShoppingPositionDTO> positions) {
        this.positions = positions;
    }
}
