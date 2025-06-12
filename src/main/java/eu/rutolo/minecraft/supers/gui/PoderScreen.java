package eu.rutolo.minecraft.supers.gui;

import java.util.List;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

public class PoderScreen extends Screen {

	protected PoderScreen(Component title) {
		super(title);
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.addRenderableWidget(new EditBox(getFont(), width/2, height/2, new Component() {

			@Override
			public Style getStyle() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ComponentContents getContents() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Component> getSiblings() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FormattedCharSequence getVisualOrderText() {
				// TODO Auto-generated method stub
				return null;
			}
			
		}))
	}

}
