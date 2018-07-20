/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChidrenTestModule } from '../../../test.module';
import { ChidMySuffixDetailComponent } from 'app/entities/chid-my-suffix/chid-my-suffix-detail.component';
import { ChidMySuffix } from 'app/shared/model/chid-my-suffix.model';

describe('Component Tests', () => {
    describe('ChidMySuffix Management Detail Component', () => {
        let comp: ChidMySuffixDetailComponent;
        let fixture: ComponentFixture<ChidMySuffixDetailComponent>;
        const route = ({ data: of({ chid: new ChidMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChidrenTestModule],
                declarations: [ChidMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChidMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChidMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.chid).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
