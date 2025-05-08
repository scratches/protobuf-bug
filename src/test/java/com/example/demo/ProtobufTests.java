package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.protobuf.MapEntry;
import com.google.protobuf.WireFormat.FieldType;

import org.junit.jupiter.api.Test;

public class ProtobufTests {

	@Test
	void testGenericMessage() throws Exception {
		MapEntry<String, GenericValue> entry = MapEntry.newDefaultInstance(GenericValue.getDescriptor(),
				FieldType.STRING, "", FieldType.MESSAGE, GenericValue.getDefaultInstance());
		GenericMessage msg = GenericMessage.newBuilder()
				.addRepeatedField(GenericMessage.getDescriptor().findFieldByName("fields"), entry.toBuilder()
						.setKey("str").setValue(GenericValue.newBuilder().setStringVal("Hello World").build()).build())
				.build();
		System.err.println(msg);
		assertThat(msg.getFieldsMap().get("str").getStringVal()).isEqualTo("Hello World");
		for (byte b : msg.toByteArray()) {
			System.err.println(b);
		}
		System.err.println(GenericMessage.getDescriptor().toProto());
	}

	@Test
	void testGenericValue() throws Exception {
		GenericValue msg = GenericValue.newBuilder().setStringVal("Hello World").build();
		System.err.println(msg);
		assertThat(msg.getStringVal()).isEqualTo("Hello World");
		for (byte b : msg.toByteArray()) {
			System.err.println(b);
		}
		System.err.println(GenericValue.getDescriptor().toProto());
	}

}
