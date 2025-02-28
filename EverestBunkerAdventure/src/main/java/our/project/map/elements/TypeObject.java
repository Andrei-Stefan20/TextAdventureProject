/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package our.project.map.elements;

/**
 *
 * Tipo enumerativo che identifica il tipo specifico di oggetto
 * 
 * @author Gruppo RES
 */
public enum TypeObject {
    
    LightingObject,PortableObject,GunObject,KeyObject,ComputerObject,FixedObject,ObjectContainer,def;
    
    public static TypeObject parseTypeObject(int typeInt){
        
        TypeObject type = def;
        
        if(typeInt == LightingObject.ordinal()){
            
            type = LightingObject;
            
        }
        
        else if(typeInt == PortableObject.ordinal()){
            
            type = PortableObject;
            
        }
        
        else if(typeInt == GunObject.ordinal()){
            
            type = GunObject;
            
        }
        
        else if(typeInt == KeyObject.ordinal()){
            
            type = KeyObject;
            
        }
        
        else if(typeInt == ComputerObject.ordinal()){
            
            type = ComputerObject;
            
        }
        
        else if(typeInt == FixedObject.ordinal()){
            
            type = FixedObject;
            
        }
        
        if(typeInt == ObjectContainer.ordinal()){
            
            type = ObjectContainer;
            
        }
        
        return type;
        
    }
    
}
