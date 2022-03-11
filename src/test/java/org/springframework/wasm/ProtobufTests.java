package org.springframework.wasm;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.protobuf.MapEntry;
import com.google.protobuf.WireFormat.FieldType;

import org.junit.jupiter.api.Test;

public class ProtobufTests {

	@Test
	void testGenericMessage() throws Exception {
		MapEntry<String, GenericValue> entry = MapEntry.newDefaultInstance(GenericValue.getDescriptor(),
				FieldType.STRING, "", FieldType.MESSAGE, null);
		GenericMessage msg = GenericMessage.newBuilder()
				.addRepeatedField(GenericMessage.getDescriptor().findFieldByName("fields"), entry.toBuilder()
						.setKey("str").setValue(GenericValue.newBuilder().setStringVal("Hello World").build()).build())
				.addRepeatedField(GenericMessage.getDescriptor().findFieldByName("fields"), entry.toBuilder()
						.setKey("obj")
						.setValue(
								GenericValue.newBuilder()
										.setMessageVal(GenericMessage.newBuilder().addRepeatedField(
												GenericMessage.getDescriptor().findFieldByName("fields"),
												entry.toBuilder()
														.setKey("str")
														.setValue(GenericValue.newBuilder().setStringVal("Bye Bye")
																.build())
														.build()))
										.build())
						.build())
				.build();
		System.err.println(msg);
		assertThat(msg.getFieldsMap().get("str").getStringVal()).isEqualTo("Hello World");
		for (byte b : msg.toByteArray()) {
			System.err.println(b);
		}
		System.err.println(GenericMessage.getDescriptor().toProto());
	}

}
