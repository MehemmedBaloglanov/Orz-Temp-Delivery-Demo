/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.intellibucket.kafka.order.avro.model.company;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CompanyOrderApproveResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5874608833332332385L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CompanyOrderApproveResponseAvroModel\",\"namespace\":\"com.intellibucket.kafka.order.avro.model.company\",\"fields\":[{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"status\",\"type\":{\"type\":\"enum\",\"name\":\"ApproveAvroStatus\",\"symbols\":[\"APPROVED\",\"REJECTED\"]}},{\"name\":\"failureMessage\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<CompanyOrderApproveResponseAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CompanyOrderApproveResponseAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CompanyOrderApproveResponseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CompanyOrderApproveResponseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CompanyOrderApproveResponseAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CompanyOrderApproveResponseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CompanyOrderApproveResponseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CompanyOrderApproveResponseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CompanyOrderApproveResponseAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID orderId;
  private com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus status;
  private java.lang.String failureMessage;
  private java.time.Instant createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CompanyOrderApproveResponseAvroModel() {}

  /**
   * All-args constructor.
   * @param orderId The new value for orderId
   * @param status The new value for status
   * @param failureMessage The new value for failureMessage
   * @param createdAt The new value for createdAt
   */
  public CompanyOrderApproveResponseAvroModel(java.util.UUID orderId, com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus status, java.lang.String failureMessage, java.time.Instant createdAt) {
    this.orderId = orderId;
    this.status = status;
    this.failureMessage = failureMessage;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return orderId;
    case 1: return status;
    case 2: return failureMessage;
    case 3: return createdAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: orderId = (java.util.UUID)value$; break;
    case 1: status = (com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus)value$; break;
    case 2: failureMessage = value$ != null ? value$.toString() : null; break;
    case 3: createdAt = (java.time.Instant)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public java.util.UUID getOrderId() {
    return orderId;
  }


  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(java.util.UUID value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'failureMessage' field.
   * @return The value of the 'failureMessage' field.
   */
  public java.lang.String getFailureMessage() {
    return failureMessage;
  }


  /**
   * Sets the value of the 'failureMessage' field.
   * @param value the value to set.
   */
  public void setFailureMessage(java.lang.String value) {
    this.failureMessage = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Creates a new CompanyOrderApproveResponseAvroModel RecordBuilder.
   * @return A new CompanyOrderApproveResponseAvroModel RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder newBuilder() {
    return new com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder();
  }

  /**
   * Creates a new CompanyOrderApproveResponseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CompanyOrderApproveResponseAvroModel RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder newBuilder(com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder other) {
    if (other == null) {
      return new com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder();
    } else {
      return new com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new CompanyOrderApproveResponseAvroModel RecordBuilder by copying an existing CompanyOrderApproveResponseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new CompanyOrderApproveResponseAvroModel RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder newBuilder(com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel other) {
    if (other == null) {
      return new com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder();
    } else {
      return new com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for CompanyOrderApproveResponseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CompanyOrderApproveResponseAvroModel>
    implements org.apache.avro.data.RecordBuilder<CompanyOrderApproveResponseAvroModel> {

    private java.util.UUID orderId;
    private com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus status;
    private java.lang.String failureMessage;
    private java.time.Instant createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.failureMessage)) {
        this.failureMessage = data().deepCopy(fields()[2].schema(), other.failureMessage);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing CompanyOrderApproveResponseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.failureMessage)) {
        this.failureMessage = data().deepCopy(fields()[2].schema(), other.failureMessage);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'orderId' field.
      * @return The value.
      */
    public java.util.UUID getOrderId() {
      return orderId;
    }


    /**
      * Sets the value of the 'orderId' field.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder setOrderId(java.util.UUID value) {
      validate(fields()[0], value);
      this.orderId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder setStatus(com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus value) {
      validate(fields()[1], value);
      this.status = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder clearStatus() {
      status = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'failureMessage' field.
      * @return The value.
      */
    public java.lang.String getFailureMessage() {
      return failureMessage;
    }


    /**
      * Sets the value of the 'failureMessage' field.
      * @param value The value of 'failureMessage'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder setFailureMessage(java.lang.String value) {
      validate(fields()[2], value);
      this.failureMessage = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'failureMessage' field has been set.
      * @return True if the 'failureMessage' field has been set, false otherwise.
      */
    public boolean hasFailureMessage() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'failureMessage' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder clearFailureMessage() {
      failureMessage = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[3], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel.Builder clearCreatedAt() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompanyOrderApproveResponseAvroModel build() {
      try {
        CompanyOrderApproveResponseAvroModel record = new CompanyOrderApproveResponseAvroModel();
        record.orderId = fieldSetFlags()[0] ? this.orderId : (java.util.UUID) defaultValue(fields()[0]);
        record.status = fieldSetFlags()[1] ? this.status : (com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus) defaultValue(fields()[1]);
        record.failureMessage = fieldSetFlags()[2] ? this.failureMessage : (java.lang.String) defaultValue(fields()[2]);
        record.createdAt = fieldSetFlags()[3] ? this.createdAt : (java.time.Instant) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CompanyOrderApproveResponseAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<CompanyOrderApproveResponseAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CompanyOrderApproveResponseAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<CompanyOrderApproveResponseAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










