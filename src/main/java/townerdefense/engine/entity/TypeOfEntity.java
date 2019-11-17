package townerdefense.engine.entity;

public enum  TypeOfEntity {
    ROAD0(0), ROAD1(1),
    ROAD2(2), ROAD3(3),
    ROAD4(4), ROAD5(5),
    ROAD6(6), TOWER(7),
    MOUNTAIN(8);
    public final int value;

    TypeOfEntity(int value) {
        this.value = value;
    }

    public static TypeOfEntity getTypeOfEntityByType(int value) {
        for(TypeOfEntity typeOfEntity : TypeOfEntity.values()) {
            if (typeOfEntity.value == value) {
                return typeOfEntity;
            }
        }
        return null;
    }

}
