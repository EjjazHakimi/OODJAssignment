/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author ejjaz
 */
interface FileHandlerInterface {
    public boolean doesRecordExists();
    public void readRecord();
    public void writeRecord();
    public void deleteRecord();
    public void updateRecord();
}
