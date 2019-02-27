package com.andrei.productapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "product code should not be null")
	@Column(name = "PCODE")
	private String productCode;

	@NotNull(message = "product name should not be null")
	@Column(name = "NAME")
	private String productName;

	@NotNull(message = "Short description code should not be null")
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@NotNull(message = "Long description should not be null")
	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "CANDISPLAY")
	private boolean canDisplay;

	@Column(name = "ISDELETED")
	private boolean isDeleted;

	@Column(name = "ISAUTOMOTIVE")
	private boolean isAutomotive;

	@Column(name = "ISINTERNATIONAL")
	private boolean isInternational;

	@OneToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
}
