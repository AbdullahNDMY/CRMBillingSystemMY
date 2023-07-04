package nttdm.bsys.common.util.dto;
import java.util.*;
import java.lang.Object;
public class AutoScaleList<E> extends ArrayList<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;
	public E get(int index){
		if(this.size() <= index){
			for(int i = this.size();i <= index; i++){
				try {
					this.add((E)object.getClass().newInstance());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return super.get(index);
	}
	public AutoScaleList(Object sample){
		this.object = sample;
	}
}
