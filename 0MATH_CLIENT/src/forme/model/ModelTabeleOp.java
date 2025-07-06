/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.MatOp;

/**
 *
 * @author User
 */
public class ModelTabeleOp extends AbstractTableModel {
    
    private List<MatOp> lista;
    private String[] kolone = {"a", "op", "b", "rez"};

    public ModelTabeleOp() {
    }

    public ModelTabeleOp(List<MatOp> lista) {
        this.lista = lista;
    }
    
    
    
    @Override
    public int getRowCount() {
        return lista.size() - 1;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MatOp m = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getA();
            case 1:
                return m.getOp();
            case 2:
                return m.getB();
            case 3:
                return m.getRez();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
